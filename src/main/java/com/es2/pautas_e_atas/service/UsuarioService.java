package com.es2.pautas_e_atas.service;

import com.es2.pautas_e_atas.domain.Usuario.DTOs.UsuarioUpdateRequestDTO;
import com.es2.pautas_e_atas.domain.Usuario.TipoUsuario;
import com.es2.pautas_e_atas.domain.Usuario.Usuario;
import com.es2.pautas_e_atas.domain.Usuario.DTOs.UsuarioDTO;
import com.es2.pautas_e_atas.domain.Usuario.DTOs.UsuarioRequestDTO;
import com.es2.pautas_e_atas.exceptions.EmailAlreadyExistsException;
import com.es2.pautas_e_atas.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(BCryptPasswordEncoder passwordEncoder, UsuarioRepository usuarioRepository) {
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario createUser(UsuarioRequestDTO data) {
        Usuario usuario = new Usuario();

        // Check if email is already in use
        if (usuarioRepository.existsByEmail(data.email())) {
            throw new EmailAlreadyExistsException("O e-mail informado já está em uso.");
        }

        usuario.setNome(data.nome());
        usuario.setEmail(data.email());
        usuario.setSenha(passwordEncoder.encode(data.senha()));
        usuario.setTipoUsuario(TipoUsuario.MEMBRO);

        return usuarioRepository.save(usuario);
    }

    public List<UsuarioDTO> listarUsuarios(String email, String role) {
        if(role.equals("GERENTE")){
            return usuarioRepository.findAll().stream()
                    .map(usuario -> new UsuarioDTO(
                            usuario.getId().toString(),
                            usuario.getNome(),
                            usuario.getEmail(),
                            usuario.getTipoUsuario().toString()
                    ))
                    .collect(Collectors.toList());
        } else {
            return usuarioRepository.findAll().stream()
                    .filter(usuario -> usuario.getEmail().equals(email))
                    .map(usuario -> new UsuarioDTO(
                            usuario.getId().toString(),
                            usuario.getNome(),
                            usuario.getEmail(),
                            usuario.getTipoUsuario().toString()
                    ))
                    .collect(Collectors.toList());
        }
    }

    public Usuario atualizarUsuario(UsuarioUpdateRequestDTO data, String emailUsuarioLogado, String idUsuario, String tipoUsuarioLogado) {
        Usuario usuarioRealizandoAcao = (Usuario) usuarioRepository.findByEmail(emailUsuarioLogado);

        if(!idUsuario.equals(usuarioRealizandoAcao.getId().toString()) && !tipoUsuarioLogado.equals("GERENTE")) {
            throw new RuntimeException("Usuário não autorizado a realizar essa ação.");
        }

        Usuario usuario = usuarioRepository.findUsuarioById(UUID.fromString(idUsuario));

        if(usuario == null) {
            throw new RuntimeException("Usuário não encontrado.");
        }

        if(!usuario.getId().toString().equals(idUsuario)) {
            throw new RuntimeException("Usuário não autorizado a realizar essa ação.");
        }

        if (!usuario.getEmail().equals(data.email()) && usuarioRepository.existsByEmail(data.email())) {
            throw new EmailAlreadyExistsException("O e-mail informado já está em uso.");
        }

        if(usuarioRealizandoAcao.getTipoUsuario() == TipoUsuario.GERENTE){
            if(data.tipoUsuario() != null){
                if(data.tipoUsuario().equals("GERENTE")){
                    usuario.setTipoUsuario(TipoUsuario.GERENTE);
                } else {
                    usuario.setTipoUsuario(TipoUsuario.MEMBRO);
                }
            }
        }

        usuario.setNome(data.nome());
        usuario.setEmail(data.email());
        usuario.setSenha(passwordEncoder.encode(data.senha()));
        return usuarioRepository.save(usuario);
    }

}

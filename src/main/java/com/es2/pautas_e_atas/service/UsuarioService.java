package com.es2.pautas_e_atas.service;

import com.es2.pautas_e_atas.domain.Usuario.TipoUsuario;
import com.es2.pautas_e_atas.domain.Usuario.Usuario;
import com.es2.pautas_e_atas.domain.Usuario.UsuarioDTO;
import com.es2.pautas_e_atas.domain.Usuario.UsuarioRequestDTO;
import com.es2.pautas_e_atas.exceptions.EmailAlreadyExistsException;
import com.es2.pautas_e_atas.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<UsuarioDTO> listarUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(usuario -> new UsuarioDTO(
                        usuario.getId().toString(),
                        usuario.getNome(),
                        usuario.getEmail(),
                        usuario.getTipoUsuario().name()))
                .collect(Collectors.toList());
    }

}

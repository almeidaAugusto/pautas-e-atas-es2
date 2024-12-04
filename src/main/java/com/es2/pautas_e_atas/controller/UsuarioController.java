package com.es2.pautas_e_atas.controller;

import com.es2.pautas_e_atas.config.TokenService;
import com.es2.pautas_e_atas.domain.Usuario.DTOs.UsuarioUpdateRequestDTO;
import com.es2.pautas_e_atas.domain.Usuario.Usuario;
import com.es2.pautas_e_atas.domain.Usuario.DTOs.UsuarioDTO;
import com.es2.pautas_e_atas.domain.Usuario.DTOs.UsuarioRequestDTO;
import com.es2.pautas_e_atas.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UsuarioRequestDTO data) {
        Usuario usuario = this.usuarioService.createUser(data);
        usuario.setSenha(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioDTO>>listarUsuarios(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "").trim();
        String email = tokenService.getEmailFromToken(token);
        String role = tokenService.getTipoUsuarioFromToken(token);
        List<UsuarioDTO> usuarios = usuarioService.listarUsuarios(email, role);
        return ResponseEntity.ok(usuarios);
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<?> atualizarUsuario(
            @RequestHeader("Authorization") String authorizationHeader,
            @PathVariable String idUsuario,
            @Valid @RequestBody UsuarioUpdateRequestDTO data) {
        try {
            // Extrair o token do header
            String token = authorizationHeader.replace("Bearer ", "").trim();

            // Obter informações do token (exemplo com JWT, ajuste conforme necessário)
            String usuarioEmailLogado = tokenService.getEmailFromToken(token);
            String tipoUsuarioLogado = tokenService.getTipoUsuarioFromToken(token);

            // Atualizar o usuário
            Usuario usuario = this.usuarioService.atualizarUsuario(data, usuarioEmailLogado, idUsuario, tipoUsuarioLogado);
            usuario.setSenha(null); // Remover a senha da resposta
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            // Retornar erro em caso de falha
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

}

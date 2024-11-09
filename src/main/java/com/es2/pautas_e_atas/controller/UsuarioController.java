package com.es2.pautas_e_atas.controller;

import com.es2.pautas_e_atas.domain.Usuario.Usuario;
import com.es2.pautas_e_atas.domain.Usuario.UsuarioRequestDTO;
import com.es2.pautas_e_atas.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UsuarioRequestDTO data) {
        Usuario usuario = this.usuarioService.createUser(data);
        usuario.setSenha(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }


}

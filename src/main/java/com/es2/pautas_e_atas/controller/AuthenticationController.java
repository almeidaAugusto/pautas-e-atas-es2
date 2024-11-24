package com.es2.pautas_e_atas.controller;

import com.es2.pautas_e_atas.config.TokenService;
import com.es2.pautas_e_atas.domain.Usuario.AuthenticationDTO;
import com.es2.pautas_e_atas.domain.Usuario.LoginResponseDTO;
import com.es2.pautas_e_atas.domain.Usuario.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Usuario) auth.getPrincipal());

        return  ResponseEntity.ok(new LoginResponseDTO(token));
    }
}

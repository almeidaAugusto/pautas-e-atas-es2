package com.es2.pautas_e_atas.controller;


import com.es2.pautas_e_atas.config.TokenService;
import com.es2.pautas_e_atas.domain.Reuniao.DTOs.*;
import com.es2.pautas_e_atas.domain.Reuniao.Reuniao;
import com.es2.pautas_e_atas.service.ReuniaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/api/reuniao")
public class ReuniaoController {
    @Autowired
    private ReuniaoService reuniaoService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<?> criarReuniao(@Valid @RequestBody ReuniaoRequestDTO data) {
        Reuniao reuniao = this.reuniaoService.criarReuniao(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(reuniao);
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listarReunioes(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "").trim();
        String email = tokenService.getEmailFromToken(token);
        String role = tokenService.getTipoUsuarioFromToken(token);
        List<ReuniaoDTO> reunioes = reuniaoService.listarReunioes(email, role);
        return ResponseEntity.ok(reunioes);
    }

    @GetMapping("/detalhes/{id}")
    public ResponseEntity<?> detalhesReuniao(@PathVariable UUID id) {
        ReuniaoDetalhesDTO reuniao = reuniaoService.detalhesReuniao(id);
        return ResponseEntity.ok(reuniao);
    }

    @PutMapping("/adicionar-ata/{id}")
    public ResponseEntity<?> adicionarAta(
            @PathVariable UUID id,
            @Valid @RequestBody AddAtaRequestDTO ataRequest,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        String token = authorizationHeader.replace("Bearer ", "").trim();

        String usuarioEmailLogado = tokenService.getEmailFromToken(token);

        var usernamePassword = new UsernamePasswordAuthenticationToken(usuarioEmailLogado, ataRequest.senha());
        try {
            authenticationManager.authenticate(usernamePassword);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("{\"error\": \"Email ou senha incorretos. Não é possível adicionar a ata.\"}");
        }

        Reuniao reuniao = reuniaoService.adicionarAta(id, ataRequest);
        return ResponseEntity.ok(reuniao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarReuniao(@PathVariable UUID id, @Valid @RequestBody ReuniaoRequestDTO data) {
        Reuniao reuniao = reuniaoService.atualizarReuniao(id, data);
        return ResponseEntity.ok(reuniao);
    }

    @PutMapping("/marcar-presenca/{id}")
    public ResponseEntity<?> marcarPresenca(@PathVariable UUID id, @RequestBody PresencaDTO data) {
        Reuniao reuniao = reuniaoService.marcarPresenca(id, data.participantes());
        return ResponseEntity.ok(reuniao);
    }
}
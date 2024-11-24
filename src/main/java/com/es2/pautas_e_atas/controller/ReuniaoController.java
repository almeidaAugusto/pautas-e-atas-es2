package com.es2.pautas_e_atas.controller;


import com.es2.pautas_e_atas.domain.Reuniao.Reuniao;
import com.es2.pautas_e_atas.domain.Reuniao.ReuniaoDTO;
import com.es2.pautas_e_atas.domain.Reuniao.ReuniaoDetalhesDTO;
import com.es2.pautas_e_atas.domain.Reuniao.ReuniaoRequestDTO;
import com.es2.pautas_e_atas.service.ReuniaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/reuniao")
public class ReuniaoController {
    @Autowired
    private ReuniaoService reuniaoService;

    @PostMapping
    public ResponseEntity <?> criarReuniao(@Valid @RequestBody ReuniaoRequestDTO data){
        Reuniao reuniao = this.reuniaoService.criarReuniao(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(reuniao);

    }

    @GetMapping ("/listar")
    public ResponseEntity<?> listarReunioes(){
        List<ReuniaoDTO> reunioes = reuniaoService.listarReunioes();
        return ResponseEntity.ok(reunioes);
    }

    @GetMapping("/detalhes/{id}")
    public ResponseEntity<?> detalhesReuniao(@PathVariable UUID id) {
            ReuniaoDetalhesDTO reuniao = reuniaoService.detalhesReuniao(id);
            return ResponseEntity.ok(reuniao);
    }
}

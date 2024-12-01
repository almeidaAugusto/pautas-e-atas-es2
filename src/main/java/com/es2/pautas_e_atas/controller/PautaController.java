package com.es2.pautas_e_atas.controller;

import com.es2.pautas_e_atas.domain.Pauta.Pauta;
import com.es2.pautas_e_atas.domain.Pauta.PautaRequestDTO;
import com.es2.pautas_e_atas.service.PautaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/pauta")
public class PautaController {
    @Autowired
    private PautaService pautaService;

    @PostMapping
    public ResponseEntity<?> criarPauta(@Valid @RequestBody PautaRequestDTO data) {
        Pauta pauta = this.pautaService.criarPauta(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(pauta);
    }
}

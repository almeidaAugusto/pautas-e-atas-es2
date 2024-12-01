package com.es2.pautas_e_atas.controller;


import com.es2.pautas_e_atas.domain.MembroParticipante.MembrosParticipantes;
import com.es2.pautas_e_atas.domain.MembroParticipante.MembrosParticipantesRequestDTO;
import com.es2.pautas_e_atas.service.MembrosParticipantesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/membros-participantes")
public class MembrosParticipantesController {
    @Autowired
    private MembrosParticipantesService membroParticipanteService;

    @PostMapping
    public ResponseEntity<?>criarMembroParticipante(@Valid @RequestBody MembrosParticipantesRequestDTO data){
        MembrosParticipantes membroParticipante = this.membroParticipanteService.criarMembroParticipante(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(membroParticipante);
    }
}

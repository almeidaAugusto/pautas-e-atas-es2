package com.es2.pautas_e_atas.service;

import com.es2.pautas_e_atas.domain.MembroParticipante.MembrosParticipantes;
import com.es2.pautas_e_atas.domain.MembroParticipante.MembrosParticipantesRequestDTO;
import com.es2.pautas_e_atas.repositories.MembrosParticipantesRepository;
import com.es2.pautas_e_atas.repositories.ReuniaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MembrosParticipantesService {
@Autowired
private MembrosParticipantesRepository membroParticipanteRepository;

@Autowired
private ReuniaoRepository reuniaoRepository;

    public MembrosParticipantes criarMembroParticipante(MembrosParticipantesRequestDTO data){
        MembrosParticipantes membroParticipante = new MembrosParticipantes();
        membroParticipante.setReuniao(reuniaoRepository.findById(data.reuniaoId()).orElseThrow(() -> new RuntimeException("Reunião não encontrada")));
        membroParticipante.setNome(data.nome());
        membroParticipante.setEmail(data.email());
        return membroParticipanteRepository.save(membroParticipante);
    }

}

package com.es2.pautas_e_atas.service;

import com.es2.pautas_e_atas.domain.Reuniao.Reuniao;
import com.es2.pautas_e_atas.domain.Reuniao.ReuniaoDTO;
import com.es2.pautas_e_atas.domain.Reuniao.ReuniaoDetalhesDTO;
import com.es2.pautas_e_atas.domain.Reuniao.ReuniaoRequestDTO;
import com.es2.pautas_e_atas.repositories.ReuniaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReuniaoService {
    @Autowired
    private ReuniaoRepository reuniaoRepository;

    public Reuniao criarReuniao(ReuniaoRequestDTO data){
        Reuniao reuniao = new Reuniao();
        reuniao.setTitulo(data.titulo());
        reuniao.setDataHora(data.dataHora());
        reuniao.setLocal(data.local());
        reuniao.setAta(data.ata());
        reuniao.setPautas(data.pautas());
        reuniao.getPautas().forEach(pauta -> pauta.setReuniao(reuniao));
        reuniao.setMembrosParticipantes(data.membrosParticipantes());
        reuniao.getMembrosParticipantes().forEach(membrosParticipantes -> membrosParticipantes.setReuniao(reuniao));
        return reuniaoRepository.save(reuniao);


    }

    public List<ReuniaoDTO> listarReunioes(){
        return reuniaoRepository.findAll().stream()
                .map(reuniao -> new ReuniaoDTO(
                        reuniao.getId().toString(),
                        reuniao.getTitulo(),
                        reuniao.getDataHora(),
                        reuniao.getLocal()
                ))
                .collect(Collectors.toList());
    }

    public ReuniaoDetalhesDTO detalhesReuniao(UUID id){
        return reuniaoRepository.findById(id).stream()
                .map(reuniao -> new ReuniaoDetalhesDTO(
                        reuniao.getId().toString(),
                        reuniao.getTitulo(),
                        reuniao.getDataHora(),
                        reuniao.getLocal(),
                        reuniao.getAta(),
                        reuniao.getPautas(),
                        reuniao.getMembrosParticipantes()
                ))
                .findFirst()
                .orElse(null);
    }

}

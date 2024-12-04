package com.es2.pautas_e_atas.service;

import com.es2.pautas_e_atas.domain.MembroParticipante.MembrosParticipantes;
import com.es2.pautas_e_atas.domain.Reuniao.DTOs.AddAtaRequestDTO;
import com.es2.pautas_e_atas.domain.Reuniao.Reuniao;
import com.es2.pautas_e_atas.domain.Reuniao.DTOs.ReuniaoDTO;
import com.es2.pautas_e_atas.domain.Reuniao.DTOs.ReuniaoDetalhesDTO;
import com.es2.pautas_e_atas.domain.Reuniao.DTOs.ReuniaoRequestDTO;
import com.es2.pautas_e_atas.repositories.ReuniaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReuniaoService {
    @Autowired
    private ReuniaoRepository reuniaoRepository;

    @Autowired
    private EmailService emailService;

    public Reuniao criarReuniao(ReuniaoRequestDTO data){
        Reuniao reuniao = new Reuniao();
        reuniao.setTitulo(data.titulo());
        reuniao.setDataHora(data.dataHora());
        reuniao.setLocal(data.local());
        reuniao.setAta(data.ata());
        reuniao.setPautas(data.pautas());
        reuniao.getPautas().forEach(pauta -> pauta.setReuniao(reuniao));
        reuniao.setMembrosParticipantes(data.membrosParticipantes());
        reuniao.getMembrosParticipantes().forEach(membrosParticipantes ->{
                membrosParticipantes.setReuniao(reuniao);
        emailService.enviarEmail(membrosParticipantes.getEmail(), "Nova reunião da associação",
                "Você foi convidado para a reunião " + reuniao.getTitulo() +
                        " no dia " + reuniao.getDataHora() + " no local " + reuniao.getLocal());
        });
        return reuniaoRepository.save(reuniao);
    }

    public List<ReuniaoDTO> listarReunioes(String email, String role){
        if(role.equals("GERENTE")){
            return reuniaoRepository.findAll().stream()
                    .map(reuniao -> new ReuniaoDTO(
                            reuniao.getId().toString(),
                            reuniao.getTitulo(),
                            reuniao.getDataHora(),
                            reuniao.getLocal()
                    ))
                    .collect(Collectors.toList());
        } else {
            return reuniaoRepository.findAll().stream()
                    .filter(reuniao -> reuniao.getMembrosParticipantes().stream()
                            .anyMatch(membrosParticipantes -> membrosParticipantes.getEmail().equals(email)))
                    .map(reuniao -> new ReuniaoDTO(
                            reuniao.getId().toString(),
                            reuniao.getTitulo(),
                            reuniao.getDataHora(),
                            reuniao.getLocal()
                    ))
                    .collect(Collectors.toList());
        }

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

    public Reuniao adicionarAta(UUID id, AddAtaRequestDTO data){
        Reuniao reuniao = reuniaoRepository.findById(id).orElse(null);
        if(reuniao != null){
            reuniao.setAta(data.ata());
            return reuniaoRepository.save(reuniao);
        }
        return null;
    }

    public Reuniao atualizarReuniao(UUID id, ReuniaoRequestDTO data){
        Reuniao reuniao = reuniaoRepository.findById(id).orElse(null);
        if(reuniao != null){
            reuniao.setTitulo(data.titulo());
            reuniao.setDataHora(data.dataHora());
            reuniao.setLocal(data.local());
            reuniao.setPautas(data.pautas());
            reuniao.getPautas().forEach(pauta -> pauta.setReuniao(reuniao));
            reuniao.setMembrosParticipantes(data.membrosParticipantes());
            reuniao.getMembrosParticipantes().forEach(membrosParticipantes -> membrosParticipantes.setReuniao(reuniao));
            return reuniaoRepository.save(reuniao);
        }
        return null;
    }

    public Reuniao marcarPresenca(UUID id, List<MembrosParticipantes> membrosParticipantes){
        Reuniao reuniao = reuniaoRepository.findById(id).orElse(null);
        if(reuniao != null){
            // Marcar presença
            reuniao.getMembrosParticipantes().forEach(membro -> {
                membrosParticipantes.forEach(membroParticipante -> {
                    if(membro.getId().equals(membroParticipante.getId())){
                        membro.setEstaPresente(membroParticipante.isEstaPresente());
                    }
                });
            });
            return reuniaoRepository.save(reuniao);
        }
        return null;
    }
}

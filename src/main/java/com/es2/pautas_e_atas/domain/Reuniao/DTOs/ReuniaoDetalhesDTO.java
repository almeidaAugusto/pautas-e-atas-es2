package com.es2.pautas_e_atas.domain.Reuniao.DTOs;

import com.es2.pautas_e_atas.domain.MembroParticipante.MembrosParticipantes;
import com.es2.pautas_e_atas.domain.Pauta.Pauta;

import java.time.LocalDateTime;
import java.util.List;

public record ReuniaoDetalhesDTO(String id,
                                 String titulo,
                                 LocalDateTime dataHora,
                                 String local,
                                 String ata,
                                 List<Pauta> pautas,
                                 List<MembrosParticipantes> membrosParticipantes) {
}

package com.es2.pautas_e_atas.domain.Reuniao;

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

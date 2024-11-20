package com.es2.pautas_e_atas.domain.Reuniao;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReuniaoDTO(String id, String titulo, LocalDateTime dataHora, String local) {
}

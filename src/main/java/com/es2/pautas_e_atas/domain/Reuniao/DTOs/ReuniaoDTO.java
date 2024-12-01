package com.es2.pautas_e_atas.domain.Reuniao.DTOs;

import java.time.LocalDateTime;

public record ReuniaoDTO(String id, String titulo, LocalDateTime dataHora, String local) {
}

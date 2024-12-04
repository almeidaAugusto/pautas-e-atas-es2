package com.es2.pautas_e_atas.domain.Reuniao.DTOs;

import com.es2.pautas_e_atas.domain.MembroParticipante.MembrosParticipantes;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record PresencaDTO(
        @NotNull
        List<MembrosParticipantes> participantes
){}

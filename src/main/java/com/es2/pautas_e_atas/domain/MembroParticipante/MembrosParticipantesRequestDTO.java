package com.es2.pautas_e_atas.domain.MembroParticipante;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record MembrosParticipantesRequestDTO(
        @NotNull(message = "ID da reunião é obrigatório")
        UUID reuniaoId,

        @NotNull(message = "Nome é obrigatório")
        String nome,

        @NotNull(message = "Email é obrigatório")
        @Email(message = "Email deve ser válido")
        String email

) {
}

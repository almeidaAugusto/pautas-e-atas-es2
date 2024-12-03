package com.es2.pautas_e_atas.domain.Reuniao.DTOs;

import jakarta.validation.constraints.NotNull;

public record AddAtaRequestDTO(
        @NotNull(message = "O conteúdo da ata não pode ser nulo")
        String ata,

        @NotNull(message = "A senha não pode ser nula")
        String senha
) {
}

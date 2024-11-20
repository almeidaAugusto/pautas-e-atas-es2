package com.es2.pautas_e_atas.domain.Reuniao;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PautaRequestDTO(@NotNull(message = "ID da reunião é obrigatório")
                              UUID reuniaoId,

                              @NotNull(message = "Título é obrigatório")
                              String titulo,

                              @NotNull(message = "Descrição é obrigatória")
                              String descricao)
{}

package com.es2.pautas_e_atas.domain.Usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record  UsuarioRequestDTO(
        @NotNull(message = "Nome é obrigatório")
        String nome,

        @NotNull(message = "Email é obrigatório")
        @Email(message = "Email deve ser válido")
        String email,

        @NotNull(message = "Senha é obrigatória")
        String senha
) {}

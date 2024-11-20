package com.es2.pautas_e_atas.domain.Reuniao;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ReuniaoRequestDTO(@NotNull(message = "Titulo é obrigatórias")
                                String titulo,

                                @NotNull(message = "Data e Hora são obrigatórias")
                                LocalDateTime dataHora,

                                @NotNull(message = "Local é obrigatório")
                                String local,


                                String ata,

                                @NotNull(message = "Pautas são obrigatórias")
                                List<Pauta> pautas,

                                @NotNull(message = "Membros Participantes são obrigatórios")
                                List<MembrosParticipantes> membrosParticipantes)
{}

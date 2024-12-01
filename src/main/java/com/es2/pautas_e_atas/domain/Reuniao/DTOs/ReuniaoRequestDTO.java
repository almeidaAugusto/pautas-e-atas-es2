package com.es2.pautas_e_atas.domain.Reuniao.DTOs;

import com.es2.pautas_e_atas.domain.MembroParticipante.MembrosParticipantes;
import com.es2.pautas_e_atas.domain.Pauta.Pauta;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

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

package com.es2.pautas_e_atas.domain.Reuniao;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Table(name = "reuniao")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reuniao {
    @Id
    @GeneratedValue

    private UUID id;
    private String titulo;
    private LocalDateTime dataHora;
    private String local;
    private String ata;

    @OneToMany(mappedBy = "reuniao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pauta> pautas;

    @OneToMany(mappedBy = "reuniao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MembrosParticipantes> membrosParticipantes;

}

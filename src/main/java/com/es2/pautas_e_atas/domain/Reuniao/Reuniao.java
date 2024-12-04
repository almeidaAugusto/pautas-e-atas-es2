package com.es2.pautas_e_atas.domain.Reuniao;


import com.es2.pautas_e_atas.domain.MembroParticipante.MembrosParticipantes;
import com.es2.pautas_e_atas.domain.Pauta.Pauta;
import com.es2.pautas_e_atas.exceptions.InvalidDateException;
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

    public void setDataHora(LocalDateTime dataHora) {
        if(dataHora.isAfter(LocalDateTime.now())){
            this.dataHora = dataHora;
        } else {
            throw new InvalidDateException("Não é possível adicionar data e hora se ela é menor que a data e hora atual");
        }
    }

    public void setMembrosParticipantes(List<MembrosParticipantes> membrosParticipantes) {
        if (this.membrosParticipantes != null) {
            this.membrosParticipantes.clear();
            if (membrosParticipantes != null) {
                this.membrosParticipantes.addAll(membrosParticipantes);
            }
        } else {
            this.membrosParticipantes = membrosParticipantes;
        }
    }

    public void setPautas(List<Pauta> pautas) {
        if (this.pautas != null) {
            this.pautas.clear();
            if (pautas != null) {
                this.pautas.addAll(pautas);
            }
        } else {
            this.pautas = pautas;
        }
    }
}

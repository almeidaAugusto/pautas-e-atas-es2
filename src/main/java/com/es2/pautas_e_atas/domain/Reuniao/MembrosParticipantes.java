package com.es2.pautas_e_atas.domain.Reuniao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Table(name = "membros_participantes")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class MembrosParticipantes {

        @Id
        @GeneratedValue
        private UUID id;

        @ManyToOne
        @JoinColumn(name = "reuniao_id", nullable = false)
        @JsonIgnore

        private Reuniao reuniao;
        private String nome;
        private String email;
}

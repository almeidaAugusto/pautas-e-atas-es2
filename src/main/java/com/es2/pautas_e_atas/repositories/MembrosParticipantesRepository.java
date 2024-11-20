package com.es2.pautas_e_atas.repositories;

import com.es2.pautas_e_atas.domain.Reuniao.MembrosParticipantes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MembrosParticipantesRepository extends JpaRepository<MembrosParticipantes, UUID> {
}

package com.es2.pautas_e_atas.repositories;

import com.es2.pautas_e_atas.domain.Pauta.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PautaRepository extends JpaRepository<Pauta, UUID> {
}

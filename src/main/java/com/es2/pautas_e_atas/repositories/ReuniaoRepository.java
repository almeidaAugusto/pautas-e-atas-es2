package com.es2.pautas_e_atas.repositories;

import com.es2.pautas_e_atas.domain.Reuniao.Reuniao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReuniaoRepository extends JpaRepository<Reuniao, UUID> {
}

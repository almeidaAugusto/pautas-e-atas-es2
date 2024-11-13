package com.es2.pautas_e_atas.repositories;

import com.es2.pautas_e_atas.domain.Usuario.TipoUsuario;
import com.es2.pautas_e_atas.domain.Usuario.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.authentication.jaas.JaasPasswordCallbackHandler;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
   boolean existsByEmail(String email);
    UserDetails findByEmail(String email);
}

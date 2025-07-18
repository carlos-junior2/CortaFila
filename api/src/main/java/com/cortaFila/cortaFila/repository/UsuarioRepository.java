package com.cortaFila.cortaFila.repository;

import com.cortaFila.cortaFila.data.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmailAndIdNot(String email, Long id);
    boolean existsByUsernameAndIdNot(String username, Long id);

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByUsername(String username);
}

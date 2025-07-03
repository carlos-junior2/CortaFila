package com.cortaFila.cortaFila.repository;

import com.cortaFila.cortaFila.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}

package com.cortaFila.cortaFila.repository;

import com.cortaFila.cortaFila.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmailAndIdNot(String email, Integer id);
    boolean existsByUsernameAndIdNot(String username, Integer id);
}

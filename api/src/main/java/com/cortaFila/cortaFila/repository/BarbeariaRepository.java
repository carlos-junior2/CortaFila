package com.cortaFila.cortaFila.repository;

import com.cortaFila.cortaFila.data.model.Barbearia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarbeariaRepository extends JpaRepository<Barbearia, Long> {

    boolean existsByEmailAndIdNot(String email, Long id);
}

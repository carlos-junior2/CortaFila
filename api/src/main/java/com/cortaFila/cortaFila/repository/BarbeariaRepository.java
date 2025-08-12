package com.cortaFila.cortaFila.repository;

import com.cortaFila.cortaFila.data.model.Barbearia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BarbeariaRepository extends JpaRepository<Barbearia, Long> {

    boolean existsByEmailAndIdNot(String email, Long id);

    @Query("SELECT DISTINCT b FROM Barbearia b LEFT JOIN FETCH b.enderecos")
    List<Barbearia> findAllComEnderecos();

    @Query("SELECT DISTINCT b FROM Barbearia b LEFT JOIN FETCH b.barbeiros")
    List<Barbearia> findAllComBarbeiros();

    @Query("SELECT DISTINCT b FROM Barbearia b LEFT JOIN FETCH b.barbeiros WHERE b.id = :id")
    Optional<Barbearia> findByIdComBarbeiros(@Param("id") Long id);

}

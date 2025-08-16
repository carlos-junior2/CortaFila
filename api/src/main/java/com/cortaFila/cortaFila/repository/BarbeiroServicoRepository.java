package com.cortaFila.cortaFila.repository;

import com.cortaFila.cortaFila.data.model.BarbeiroServico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BarbeiroServicoRepository extends JpaRepository<BarbeiroServico, Long> {

    List<BarbeiroServico> findByBarbeiroId(Long BarbeiroId);
}

package com.cortaFila.cortaFila.repository;

import com.cortaFila.cortaFila.data.model.Barbearia;
import com.cortaFila.cortaFila.data.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}

package com.cortaFila.cortaFila.repository;

import com.cortaFila.cortaFila.data.model.Agendamento;
import com.cortaFila.cortaFila.data.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    boolean existsByBarbeiroIdAndDataAndHorario(Long barbeiroId, LocalDate data, LocalTime horario);

    List<Agendamento> findByBarbeiroIdAndData(Long idBarbeiro, LocalDate data);

    Page<Agendamento> findByUsuario(Usuario usuario, Pageable pageable);
}

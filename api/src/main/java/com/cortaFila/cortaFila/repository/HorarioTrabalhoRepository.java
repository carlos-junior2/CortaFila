package com.cortaFila.cortaFila.repository;

import com.cortaFila.cortaFila.data.model.DiaSemana;
import com.cortaFila.cortaFila.data.model.HorarioTrabalho;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface HorarioTrabalhoRepository extends JpaRepository<HorarioTrabalho, Long> {

    List<HorarioTrabalho> findByBarbeiroIdAndDiaSemana(Long barbeiroId, DiaSemana diaSemana);
}

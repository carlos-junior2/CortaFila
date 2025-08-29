package com.cortaFila.cortaFila.service;

import com.cortaFila.cortaFila.data.model.DiaSemana;
import com.cortaFila.cortaFila.data.model.HorarioTrabalho;
import com.cortaFila.cortaFila.exception.RegistroNaoEncontradoException;
import com.cortaFila.cortaFila.repository.HorarioTrabalhoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HorarioTrabalhoService {

    private final HorarioTrabalhoRepository horarioTrabalhoRepository;

    public List<HorarioTrabalho> buscarHorariosPorBarbeiro(Long idBarbeiro, DiaSemana dia){
        return horarioTrabalhoRepository.findByBarbeiroIdAndDiaSemana(idBarbeiro, dia);
    }

    public void salvarHorarios(List<HorarioTrabalho> horarios){
        horarioTrabalhoRepository.saveAll(horarios);
    }
}

package com.cortaFila.cortaFila.service;

import com.cortaFila.cortaFila.data.model.HorarioTrabalho;
import com.cortaFila.cortaFila.repository.HorarioTrabalhoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HorarioTrabalhoService {

    private final HorarioTrabalhoRepository horarioTrabalhoRepository;

    public void salvarHorarios(List<HorarioTrabalho> horarios){
        horarioTrabalhoRepository.saveAll(horarios);
    }
}

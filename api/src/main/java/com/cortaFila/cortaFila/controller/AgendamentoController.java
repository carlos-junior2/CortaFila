package com.cortaFila.cortaFila.controller;

import com.cortaFila.cortaFila.data.dto.AgendamentoRequestDTO;
import com.cortaFila.cortaFila.data.dto.AgendamentoResponseDTO;
import com.cortaFila.cortaFila.service.AgendamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/agendamentos")
@RequiredArgsConstructor
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @PostMapping
    public ResponseEntity<AgendamentoResponseDTO> salvarAgendamento(@Valid @RequestBody AgendamentoRequestDTO dto){
        AgendamentoResponseDTO response = agendamentoService.criarAgendamento(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/barbeiro/{idBarbeiro}")
    public ResponseEntity<List<AgendamentoResponseDTO>> listarPorBarbeiroEData(@PathVariable Long idBarbeiro, @RequestParam LocalDate data){
        List<AgendamentoResponseDTO> agendamentos = agendamentoService.listarAgendamentosPorBarbeiro(idBarbeiro, data);
        return ResponseEntity.ok(agendamentos);
    }

    @GetMapping("/disponiveis/{idBarbeiro}")
    public ResponseEntity<List<LocalTime>> buscarHorariosDisponiveis(
            @PathVariable Long idBarbeiro,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data,
            @RequestParam Long idServico){
        List<LocalTime> horarios = agendamentoService.buscarHorariosDisponiveis(idBarbeiro, data, idServico);
        return ResponseEntity.ok(horarios);
    }
}

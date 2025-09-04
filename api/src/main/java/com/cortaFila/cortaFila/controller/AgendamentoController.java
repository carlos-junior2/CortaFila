package com.cortaFila.cortaFila.controller;

import com.cortaFila.cortaFila.data.dto.AgendamentoRequestDTO;
import com.cortaFila.cortaFila.data.dto.AgendamentoResponseDTO;
import com.cortaFila.cortaFila.service.AgendamentoService;
import com.cortaFila.cortaFila.service.TwilioSmsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/agendamentos")
@RequiredArgsConstructor
@Tag(name = "Agendamentos")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;
    private final TwilioSmsService smsService;

    @PostMapping
    @Operation(summary = "Salvar", description = "Salvar um novo Agendamento")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Agendamento criado com sucesso."),
            @ApiResponse(responseCode = "409", description = "Já existe um agendamento para este barbeiro neste dia e horário.")
    })
    public ResponseEntity<AgendamentoResponseDTO> salvarAgendamento(@Valid @RequestBody AgendamentoRequestDTO dto){
        AgendamentoResponseDTO response = agendamentoService.criarAgendamento(dto);

        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd 'de' MMMM, EEEE", new Locale("pt", "BR"));

        String mensagem = String.format(
                "Olá %s, o cliente %s agendou um serviço de %s às %s no dia %s.",
                response.barbeiroNome(),
                response.usuarioNome(),
                response.servicoNome(),
                response.inicio().format(formatterHora),
                response.data().format(formatterData)
        );

        smsService.sendSms("+5535988054493", mensagem);


        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/barbeiro/{idBarbeiro}")
    @Operation(summary = "Listar Agendamentos por Barbeiros", description = "Listar Agendamentos por Barbeiros e Data")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK."),
            @ApiResponse(responseCode = "404", description = "Barbeiro não encontrado.")
    })
    public ResponseEntity<List<AgendamentoResponseDTO>> listarPorBarbeiroEData(@PathVariable Long idBarbeiro, @RequestParam LocalDate data){
        List<AgendamentoResponseDTO> agendamentos = agendamentoService.listarAgendamentosPorBarbeiro(idBarbeiro, data);
        return ResponseEntity.ok(agendamentos);
    }

    @GetMapping("/usuario/{idUsuario}")
    @Operation(summary = "Listar Agendamentos por Usuários", description = "Listar Agendamentos por Usuários")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK."),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado.")
    })
    public ResponseEntity<Page<AgendamentoResponseDTO>> listarPorUsuario(
            @PathVariable Long idUsuario,
            @PageableDefault(
                    page = 0,
                    size = 5,
                    sort = {"data", "horario"})
                    Pageable pageable){
        Page<AgendamentoResponseDTO> agendamentos = agendamentoService.listarAgendamentosPorUsuario(idUsuario, pageable);
        return ResponseEntity.ok(agendamentos);
    }

    @GetMapping("/disponiveis/{idBarbeiro}")
    @Operation(summary = "Listar horários disponíveis", description = "Listar horários disponíveis por Barbeiro e Data")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK."),
            @ApiResponse(responseCode = "404", description = "Horários de trabalho não cadastrados para este dia.")
    })
    public ResponseEntity<List<LocalTime>> buscarHorariosDisponiveis(
            @PathVariable Long idBarbeiro,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data,
            @RequestParam Long idServico){
        List<LocalTime> horarios = agendamentoService.buscarHorariosDisponiveis(idBarbeiro, data, idServico);
        return ResponseEntity.ok(horarios);
    }
}

package com.cortaFila.cortaFila.data.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

@Schema(name = "Agendamento")
public record AgendamentoRequestDTO(
        @NotNull(message = "O barbeiro é obrigatório")
        Long idBarbeiro,

        Long idUsuario,

        @NotNull(message = "O tipo de serviço é obrigatório")
        Long idBarbeiroServico,

        @NotNull(message = "A data é obrigatória")
        @FutureOrPresent(message = "A data deve ser hoje ou futura")
        LocalDate data,

        @NotNull(message = "O horário é obrigatório.")
        LocalTime horario
) {}

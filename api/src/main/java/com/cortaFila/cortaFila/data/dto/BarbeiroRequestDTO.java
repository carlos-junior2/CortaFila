package com.cortaFila.cortaFila.data.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record BarbeiroRequestDTO(
        @NotNull(message = "O id da barbearia é obrigatório")
        Long idBarbearia,

        @NotNull(message = "O usuário é obrigatório")
        @Valid
        UsuarioRequestDTO usuario,

        @NotEmpty(message = "É necessário informar pelo menos um horário de trabalho")
        List<@Valid HorarioTrabalhoDTO> horarios
){}
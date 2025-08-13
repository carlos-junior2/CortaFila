package com.cortaFila.cortaFila.data.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record BarbeiroRequestDTO(
        @NotNull(message = "O id da barbearia é obrigatório")
        Long idBarbearia,

        @NotNull(message = "O usuário é obrigatório")
        @Valid
        UsuarioRequestDTO usuario
){}
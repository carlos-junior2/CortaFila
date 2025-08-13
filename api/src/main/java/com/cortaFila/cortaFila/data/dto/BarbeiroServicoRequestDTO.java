package com.cortaFila.cortaFila.data.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record BarbeiroServicoRequestDTO(
        @NotNull(message = "O id do barbeiro é obrigatório")
        Long barbeiroId,

        @NotNull(message = "O id do tipo de serviço é obrigatório")
        Long tipoServicoId,

        @NotNull(message = "O preço é obrigatório")
        @DecimalMin(value = "0.0", inclusive = false, message = "O preço deve ser maior que 0")
        BigDecimal preco,

        @NotNull(message = "A duração é obrigatória")
        @Min(value = 1, message = "A duração deve ser no mínimo 1 minuto")
        Integer duracaoMin
) {}

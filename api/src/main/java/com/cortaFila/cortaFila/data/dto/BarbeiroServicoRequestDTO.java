package com.cortaFila.cortaFila.data.dto;

import java.math.BigDecimal;

public record BarbeiroServicoRequestDTO(
        Long barbeiroId,
        Long tipoServicoId,
        BigDecimal preco,
        Integer duracaoMin
) {}

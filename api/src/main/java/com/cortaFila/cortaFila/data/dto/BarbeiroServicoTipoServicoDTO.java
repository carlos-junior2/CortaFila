package com.cortaFila.cortaFila.data.dto;

import java.math.BigDecimal;

public record BarbeiroServicoTipoServicoDTO(
        Long idServico,
        BigDecimal preco,
        Integer duracaoMin,
        Long idTipoServico,
        String nomeTipoServico,
        String descricao
) {}

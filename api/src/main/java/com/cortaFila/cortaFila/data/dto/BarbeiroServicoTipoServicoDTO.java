package com.cortaFila.cortaFila.data.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(name = "Barbeiro Serviço com Tipo Serviço")
public record BarbeiroServicoTipoServicoDTO(
        Long idServico,
        BigDecimal preco,
        Integer duracaoMin,
        Long idTipoServico,
        String nomeTipoServico,
        String descricao
) {}

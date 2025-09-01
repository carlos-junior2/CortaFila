package com.cortaFila.cortaFila.data.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Tipo Serviço Resposta")
public record TipoServicoResponseDTO(
        Long id,
        String nome,
        String descricao
) {
}

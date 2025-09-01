package com.cortaFila.cortaFila.data.dto;

import com.cortaFila.cortaFila.data.model.BarbeiroServico;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(name = "Barbeiro Serviço Resposta")
public record BarbeiroServicoResponseDTO(
        Long id,
        Long barbeiroId,
        Long tipoServicoId,
        BigDecimal preco,
        Integer duracaoMin
) {
    public BarbeiroServicoResponseDTO(BarbeiroServico barbeiroServico){
        this(
            barbeiroServico.getId(),
            barbeiroServico.getBarbeiro().getId(),
            barbeiroServico.getTipoServico().getId(),
            barbeiroServico.getPreco(),
            barbeiroServico.getDuracaoMin()
        );
    }
}

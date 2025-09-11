package com.cortaFila.cortaFila.data.dto;

import com.cortaFila.cortaFila.data.model.BarbeiroServico;
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
) {
    public BarbeiroServicoTipoServicoDTO(BarbeiroServico bs){
        this(
                bs.getId(),
                bs.getPreco(),
                bs.getDuracaoMin(),
                bs.getTipoServico().getId(),
                bs.getTipoServico().getNome(),
                bs.getTipoServico().getDescricao()
        );
    }
}

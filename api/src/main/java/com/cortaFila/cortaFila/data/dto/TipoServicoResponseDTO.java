package com.cortaFila.cortaFila.data.dto;

import com.cortaFila.cortaFila.data.model.TipoServico;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Tipo Serviço Resposta")
public record TipoServicoResponseDTO(
        Long id,
        String nome,
        String descricao
) {
    public TipoServicoResponseDTO(TipoServico ts){
        this(
                ts.getId(),
                ts.getNome(),
                ts.getDescricao()
        );
    }
}

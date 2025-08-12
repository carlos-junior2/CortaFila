package com.cortaFila.cortaFila.data.dto;

import com.cortaFila.cortaFila.data.model.TipoServico;

public record TipoServicoRequestDTO(
        String nome,
        String descricao
) {
    public TipoServico toEntity(){
        TipoServico tipoServico = new TipoServico();
        tipoServico.setNome(this.nome());
        tipoServico.setDescricao(this.descricao());

        return tipoServico;
    }
}

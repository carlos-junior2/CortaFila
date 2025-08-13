package com.cortaFila.cortaFila.data.dto;

import com.cortaFila.cortaFila.data.model.TipoServico;
import jakarta.validation.constraints.NotBlank;

public record TipoServicoRequestDTO(
        @NotBlank(message = "Nome não pode estar vazio")
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

package com.cortaFila.cortaFila.data.dto;

import com.cortaFila.cortaFila.data.model.Barbearia;
import com.cortaFila.cortaFila.data.model.Endereco;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(name = "Barbearia Resposta")
public record BarbeariaResponseDTO(
        Long id,
        String nome,
        String descricao,
        String email,
        String imagemPatch,
        List<EnderecoResponseDTO> enderecos
) {
    public BarbeariaResponseDTO(Barbearia b, Endereco endereco) {
        this(
                b.getId(),
                b.getNome(),
                b.getDescricao(),
                b.getEmail(),
                b.getImagemPatch(),
                endereco != null ? List.of(new EnderecoResponseDTO(endereco)) : List.of()
        );
    }
}


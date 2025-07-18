package com.cortaFila.cortaFila.data.dto;

import com.cortaFila.cortaFila.data.model.Barbearia;
import com.cortaFila.cortaFila.data.model.Endereco;

import java.util.List;

public record BarbeariaResponseDTO(
        Long id,
        String nome,
        String descricao,
        String email,
        String imagemPatch,
        List<EnderecoDTO> enderecos
) {
    public BarbeariaResponseDTO(Barbearia b, Endereco endereco) {
        this(
                b.getId(),
                b.getNome(),
                b.getDescricao(),
                b.getEmail(),
                b.getImagemPatch(),
                endereco != null ? List.of(new EnderecoDTO(endereco)) : List.of()
        );
    }
}


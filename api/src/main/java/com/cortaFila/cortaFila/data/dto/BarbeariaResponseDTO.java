package com.cortaFila.cortaFila.data.dto;

import com.cortaFila.cortaFila.data.model.Barbearia;
import com.cortaFila.cortaFila.data.model.Endereco;

public record BarbeariaResponseDTO(
        Long id,
        String nome,
        String descricao,
        String email,
        String imagemPatch,
        EnderecoDTO endereco
) {
    public BarbeariaResponseDTO(Barbearia b, Endereco endereco) {
        this(
                b.getId(),
                b.getNome(),
                b.getDescricao(),
                b.getEmail(),
                b.getImagemPatch(),
                endereco != null ? new EnderecoDTO(endereco) : null
        );
    }
}


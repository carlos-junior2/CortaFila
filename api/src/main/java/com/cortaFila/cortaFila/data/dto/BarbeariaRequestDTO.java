package com.cortaFila.cortaFila.data.dto;

import com.cortaFila.cortaFila.data.model.Barbearia;
import com.cortaFila.cortaFila.data.model.Endereco;

public record BarbeariaRequestDTO(
        String nome,
        String descricao,
        String email,
        String imagemPatch,
        EnderecoDTO endereco
) {
    public Barbearia toEntity() {
        Barbearia barbearia = new Barbearia();
        barbearia.setNome(this.nome());
        barbearia.setEmail(this.email());
        barbearia.setDescricao(this.descricao());
        barbearia.setImagemPatch(this.imagemPatch());

        if (this.endereco() != null && this.endereco().temDadosValidos()) {
            Endereco enderecoEntity = this.endereco().toEntity();
            enderecoEntity.setBarbearia(barbearia);
            barbearia.getEnderecos().add(enderecoEntity);
        }

        return barbearia;
    }
}


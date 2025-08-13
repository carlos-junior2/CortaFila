package com.cortaFila.cortaFila.data.dto;

import com.cortaFila.cortaFila.data.model.Barbearia;
import com.cortaFila.cortaFila.data.model.Endereco;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record BarbeariaRequestDTO(
        @NotBlank(message = "Nome não pode estar vazio")
        String nome,

        String descricao,

        @Email(message = "Email inválido")
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


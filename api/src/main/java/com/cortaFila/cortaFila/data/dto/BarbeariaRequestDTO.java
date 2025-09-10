package com.cortaFila.cortaFila.data.dto;

import com.cortaFila.cortaFila.data.model.Barbearia;
import com.cortaFila.cortaFila.data.model.Endereco;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(name = "Barbearia")
public record BarbeariaRequestDTO(
        @NotBlank(message = "Nome não pode estar vazio")
        String nome,

        String descricao,

        @Email(message = "Email inválido")
        String email,

        String imagemPatch,
        EnderecoRequestDTO endereco
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


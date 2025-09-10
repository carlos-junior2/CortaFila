package com.cortaFila.cortaFila.data.dto;

import com.cortaFila.cortaFila.data.model.Endereco;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Endereço Resposta")
public record EnderecoResponseDTO(
        Long id,
        String logradouro,
        String bairro,
        String cidade,
        String estado,
        String cep,
        String pontoDeReferencia,
        String telefone
){
    public EnderecoResponseDTO(Endereco e) {
        this(e.getId(), e.getLogradouro(), e.getBairro(), e.getCidade(), e.getEstado(), e.getCep(), e.getPontoDeReferencia(), e.getTelefone());
    }

    public Endereco toEntity() {
        Endereco endereco = new Endereco();
        endereco.setLogradouro(logradouro);
        endereco.setBairro(bairro);
        endereco.setCidade(cidade);
        endereco.setEstado(estado);
        endereco.setCep(cep);
        endereco.setPontoDeReferencia(pontoDeReferencia);
        endereco.setTelefone(telefone);
        return endereco;
    }

    public static EnderecoResponseDTO fromEntity(Endereco endereco) {
        return new EnderecoResponseDTO(
                endereco.getId(),
                endereco.getLogradouro(),
                endereco.getBairro(),
                endereco.getCidade(),
                endereco.getEstado(),
                endereco.getCep(),
                endereco.getPontoDeReferencia(),
                endereco.getTelefone()
        );
    }

}

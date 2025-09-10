package com.cortaFila.cortaFila.data.dto;

import com.cortaFila.cortaFila.data.model.Endereco;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Endereço")
public record EnderecoRequestDTO(
        String logradouro,
        String bairro,
        String cidade,
        String estado,
        String cep,
        String pontoDeReferencia,
        String telefone
) {
    public EnderecoRequestDTO(Endereco e) {
        this(e.getLogradouro(), e.getBairro(), e.getCidade(), e.getEstado(), e.getCep(), e.getPontoDeReferencia(), e.getTelefone());
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

    public boolean temDadosValidos() {
        return cep != null && !cep.isBlank();
    }
}

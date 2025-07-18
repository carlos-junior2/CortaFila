package com.cortaFila.cortaFila.data.dto;

import com.cortaFila.cortaFila.data.model.Endereco;

public record EnderecoDTO(
        String logradouro,
        String bairro,
        String cidade,
        String estado,
        String cep,
        String pontoDeReferencia,
        String telefone
){
    public EnderecoDTO(Endereco e) {
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

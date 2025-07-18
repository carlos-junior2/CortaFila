package com.cortaFila.cortaFila.service;

import com.cortaFila.cortaFila.data.model.Endereco;
import com.cortaFila.cortaFila.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    public Endereco salvar(Endereco endereco){
        return enderecoRepository.save(endereco);
    }

}

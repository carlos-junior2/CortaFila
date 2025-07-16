package com.cortaFila.cortaFila.service;

import com.cortaFila.cortaFila.data.dto.BarbeariaRequestDTO;
import com.cortaFila.cortaFila.data.dto.BarbeariaResponseDTO;
import com.cortaFila.cortaFila.data.model.Barbearia;
import com.cortaFila.cortaFila.data.model.Endereco;
import com.cortaFila.cortaFila.repository.BarbeariaRepository;
import com.cortaFila.cortaFila.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BarbeariaService {

    private final BarbeariaRepository barbeariaRepository;
    private final EnderecoRepository enderecoRepository;

    public BarbeariaResponseDTO salvar(BarbeariaRequestDTO dto){
        Barbearia barbearia = dto.toEntity();
        Barbearia barbeariaSalva = barbeariaRepository.save(barbearia);

        Endereco endereco = dto.endereco().toEntity();
        endereco.setBarbearia(barbeariaSalva);
        Endereco enderecoSalvo = enderecoRepository.save(endereco);

        return new BarbeariaResponseDTO(barbeariaSalva, enderecoSalvo);
    }


}

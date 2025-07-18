package com.cortaFila.cortaFila.service;

import com.cortaFila.cortaFila.data.dto.BarbeariaRequestDTO;
import com.cortaFila.cortaFila.data.dto.BarbeariaResponseDTO;
import com.cortaFila.cortaFila.data.model.Barbearia;
import com.cortaFila.cortaFila.data.model.Endereco;
import com.cortaFila.cortaFila.exception.RegistroDuplicadoException;
import com.cortaFila.cortaFila.repository.BarbeariaRepository;
import com.cortaFila.cortaFila.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BarbeariaService {

    private final BarbeariaRepository barbeariaRepository;
    private final EnderecoRepository enderecoRepository;

    public BarbeariaResponseDTO salvar(BarbeariaRequestDTO dto){
        validarDuplicidade(dto.email(), null);
        Barbearia barbearia = dto.toEntity();
        Barbearia barbeariaSalva = barbeariaRepository.save(barbearia);

        if(dto.endereco() != null && dto.endereco().temDadosValidos()){
            Endereco endereco = dto.endereco().toEntity();
            endereco.setBarbearia(barbeariaSalva);
            Endereco enderecoSalvo = enderecoRepository.save(endereco);
            return new BarbeariaResponseDTO(barbeariaSalva, enderecoSalvo);
        }
        return new BarbeariaResponseDTO(barbeariaSalva, null);
    }

    public Optional<Barbearia> buscarPorId(Long id){
        return barbeariaRepository.findById(id);
    }

    private void validarDuplicidade(String email, Long id) {
        if (barbeariaRepository.existsByEmailAndIdNot(email, id)) {
            throw new RegistroDuplicadoException("Já existe uma barbearia cadastrada com este email!");
        }
    }

}

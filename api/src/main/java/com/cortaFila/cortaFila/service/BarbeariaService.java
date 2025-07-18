package com.cortaFila.cortaFila.service;

import com.cortaFila.cortaFila.data.dto.BarbeariaRequestDTO;
import com.cortaFila.cortaFila.data.dto.BarbeariaResponseDTO;
import com.cortaFila.cortaFila.data.dto.EnderecoDTO;
import com.cortaFila.cortaFila.data.model.Barbearia;
import com.cortaFila.cortaFila.data.model.Endereco;
import com.cortaFila.cortaFila.exception.RegistroDuplicadoException;
import com.cortaFila.cortaFila.repository.BarbeariaRepository;
import com.cortaFila.cortaFila.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BarbeariaService {

    private final BarbeariaRepository barbeariaRepository;
    private final EnderecoRepository enderecoRepository;

    public BarbeariaResponseDTO salvar(BarbeariaRequestDTO dto) {
        validarDuplicidade(dto.email(), null);
        Barbearia barbearia = dto.toEntity();
        Barbearia barbeariaSalva = barbeariaRepository.save(barbearia);

        Endereco endereco = barbeariaSalva.getEnderecos().stream().findFirst().orElse(null);

        return new BarbeariaResponseDTO(barbeariaSalva, endereco);
    }


    public Optional<Barbearia> buscarPorId(Long id){
        return barbeariaRepository.findById(id);
    }

    public List<BarbeariaResponseDTO> listar(){
        List<Barbearia> barbearias = barbeariaRepository.findAllComEnderecos();

        return barbearias.stream()
                .map(barbearia -> {
                    List<EnderecoDTO> enderecosDTO = barbearia.getEnderecos().stream()
                            .map(EnderecoDTO::fromEntity)
                            .collect(Collectors.toList());

                    return new BarbeariaResponseDTO(
                            barbearia.getId(),
                            barbearia.getNome(),
                            barbearia.getDescricao(),
                            barbearia.getEmail(),
                            barbearia.getImagemPatch(),
                            enderecosDTO
                    );
                }).collect(Collectors.toList());
    }

    private void validarDuplicidade(String email, Long id) {
        if (barbeariaRepository.existsByEmailAndIdNot(email, id)) {
            throw new RegistroDuplicadoException("Já existe uma barbearia cadastrada com este email!");
        }
    }

}

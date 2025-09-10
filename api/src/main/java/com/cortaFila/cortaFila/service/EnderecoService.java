package com.cortaFila.cortaFila.service;

import com.cortaFila.cortaFila.data.dto.EnderecoRequestDTO;
import com.cortaFila.cortaFila.data.dto.EnderecoResponseDTO;
import com.cortaFila.cortaFila.data.model.Endereco;
import com.cortaFila.cortaFila.exception.RegistroNaoEncontradoException;
import com.cortaFila.cortaFila.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    public EnderecoResponseDTO salvar(Endereco endereco){
        Endereco salvo = enderecoRepository.save(endereco);
        return new EnderecoResponseDTO(salvo);
    }

    public Page<EnderecoResponseDTO> listar(Pageable pageable){
        return enderecoRepository.findAll(pageable)
                .map(EnderecoResponseDTO::new);
    }

    public EnderecoResponseDTO buscarPorId(Long id){
        Endereco endereco = this.findById(id);
        return new EnderecoResponseDTO(endereco);
    }

    public EnderecoResponseDTO atualizar(Long id, EnderecoRequestDTO dto){
        Endereco endereco = this.findById(id);
        endereco.setLogradouro(dto.logradouro());
        endereco.setBairro(dto.bairro());
        endereco.setCidade(dto.cidade());
        endereco.setEstado(dto.estado());
        endereco.setCep(dto.cep());
        endereco.setPontoDeReferencia(dto.pontoDeReferencia());
        endereco.setTelefone(dto.telefone());

        Endereco atualizado = enderecoRepository.save(endereco);

        return new EnderecoResponseDTO(atualizado);
    }

    public void excluir(Long id){
        Endereco endereco = this.findById(id);
        enderecoRepository.delete(endereco);
    }

    private Endereco findById(Long id){
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Endereço não encontrado!"));
    }
}

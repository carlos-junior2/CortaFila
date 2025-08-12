package com.cortaFila.cortaFila.service;

import com.cortaFila.cortaFila.data.dto.TipoServicoRequestDTO;
import com.cortaFila.cortaFila.data.dto.TipoServicoResponseDTO;
import com.cortaFila.cortaFila.data.model.Barbeiro;
import com.cortaFila.cortaFila.data.model.TipoServico;
import com.cortaFila.cortaFila.exception.RegistroNaoEncontradoException;
import com.cortaFila.cortaFila.repository.TipoServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TipoServicoService {

    private final TipoServicoRepository tipoServicoRepository;

    public TipoServico buscarPorId(Long id){
        return tipoServicoRepository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Barbeiro não encontrado!"));
    }

    public TipoServicoResponseDTO salvar(TipoServicoRequestDTO dto){
        TipoServico tipoServico = new TipoServico();
        tipoServico = dto.toEntity();
        tipoServicoRepository.save(tipoServico);
        TipoServicoResponseDTO dtoSaida = new TipoServicoResponseDTO(
                tipoServico.getId(),
                tipoServico.getNome(),
                tipoServico.getDescricao()
        );
        return dtoSaida;
    }
}

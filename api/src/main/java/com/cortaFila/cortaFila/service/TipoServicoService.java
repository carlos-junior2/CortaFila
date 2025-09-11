package com.cortaFila.cortaFila.service;

import com.cortaFila.cortaFila.data.dto.TipoServicoRequestDTO;
import com.cortaFila.cortaFila.data.dto.TipoServicoResponseDTO;
import com.cortaFila.cortaFila.data.model.TipoServico;
import com.cortaFila.cortaFila.exception.RegistroNaoEncontradoException;
import com.cortaFila.cortaFila.repository.TipoServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TipoServicoService {

    private final TipoServicoRepository tipoServicoRepository;

    public TipoServico buscarPorId(Long id){
        return tipoServicoRepository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Tipo Serviço não encontrado!"));
    }

    public TipoServicoResponseDTO salvar(TipoServicoRequestDTO dto){
        TipoServico tipoServico = dto.toEntity();
        tipoServicoRepository.save(tipoServico);
        return new TipoServicoResponseDTO(
                tipoServico.getId(),
                tipoServico.getNome(),
                tipoServico.getDescricao()
        );
    }

    public Page<TipoServicoResponseDTO> listar(Pageable pageable){
        return tipoServicoRepository.findAll(pageable)
                .map(TipoServicoResponseDTO::new);
    }

    public TipoServicoResponseDTO buscar(Long id){
        TipoServico ts = this.buscarPorId(id);
        return new TipoServicoResponseDTO(ts);
    }

    public TipoServicoResponseDTO atualizar(Long id, TipoServicoRequestDTO dto){
        TipoServico ts = this.buscarPorId(id);
        ts.setNome(dto.nome());
        ts.setDescricao(dto.descricao());

        TipoServico atualizado = tipoServicoRepository.save(ts);

        return new TipoServicoResponseDTO(atualizado);
    }
}

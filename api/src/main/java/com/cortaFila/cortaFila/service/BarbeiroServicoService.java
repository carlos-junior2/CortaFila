package com.cortaFila.cortaFila.service;

import com.cortaFila.cortaFila.data.dto.BarbeiroServicoResponseDTO;
import com.cortaFila.cortaFila.data.dto.BarbeiroServicoRequestDTO;
import com.cortaFila.cortaFila.data.dto.BarbeiroServicoTipoServicoDTO;
import com.cortaFila.cortaFila.data.model.Barbeiro;
import com.cortaFila.cortaFila.data.model.BarbeiroServico;
import com.cortaFila.cortaFila.data.model.TipoServico;
import com.cortaFila.cortaFila.exception.RegistroNaoEncontradoException;
import com.cortaFila.cortaFila.repository.BarbeiroServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BarbeiroServicoService {

    private final BarbeiroServicoRepository barbeiroServicoRepository;
    private final BarbeiroService barbeiroService;
    private final TipoServicoService tipoServicoService;

    public BarbeiroServicoResponseDTO salvar(BarbeiroServicoRequestDTO dto){
        Barbeiro barbeiro = barbeiroService.buscarPorId(dto.barbeiroId());
        TipoServico tipoServico = tipoServicoService.buscarPorId(dto.tipoServicoId());

        BarbeiroServico barbeiroServico = new BarbeiroServico();
        barbeiroServico.setBarbeiro(barbeiro);
        barbeiroServico.setTipoServico(tipoServico);
        barbeiroServico.setPreco(dto.preco());
        barbeiroServico.setDuracaoMin(dto.duracaoMin());

        barbeiroServicoRepository.save(barbeiroServico);

        return new BarbeiroServicoResponseDTO(barbeiroServico);
    }

    public List<BarbeiroServicoTipoServicoDTO> buscarServicosPorIdBarbeiro(Long idBarbeiro){
        //Para gerar exception, se for o caso
        barbeiroService.buscarPorId(idBarbeiro);

        return barbeiroServicoRepository.findByBarbeiroId(idBarbeiro)
                .stream()
                .map(sb -> new BarbeiroServicoTipoServicoDTO(
                        sb.getId(),
                        sb.getPreco(),
                        sb.getDuracaoMin(),
                        sb.getTipoServico().getId(),
                        sb.getTipoServico().getNome(),
                        sb.getTipoServico().getDescricao()
                ))
                .toList();

    }

    public BarbeiroServico buscarBarbeiroServicoPorId(Long barbeiroServicoId){
        return barbeiroServicoRepository.findById(barbeiroServicoId)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Barbeiro Serviço não encontrado!"));
    }
}

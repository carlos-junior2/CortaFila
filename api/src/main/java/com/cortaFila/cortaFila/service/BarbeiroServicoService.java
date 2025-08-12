package com.cortaFila.cortaFila.service;

import com.cortaFila.cortaFila.data.dto.BarbeiroServicoResponseDTO;
import com.cortaFila.cortaFila.data.dto.BarbeiroServicoRequestDTO;
import com.cortaFila.cortaFila.data.model.Barbeiro;
import com.cortaFila.cortaFila.data.model.BarbeiroServico;
import com.cortaFila.cortaFila.data.model.TipoServico;
import com.cortaFila.cortaFila.repository.BarbeiroServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}

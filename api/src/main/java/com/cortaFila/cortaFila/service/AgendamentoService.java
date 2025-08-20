package com.cortaFila.cortaFila.service;

import com.cortaFila.cortaFila.data.dto.AgendamentoRequestDTO;
import com.cortaFila.cortaFila.data.dto.AgendamentoResponseDTO;
import com.cortaFila.cortaFila.data.model.Agendamento;
import com.cortaFila.cortaFila.data.model.Barbeiro;
import com.cortaFila.cortaFila.data.model.BarbeiroServico;
import com.cortaFila.cortaFila.data.model.Usuario;
import com.cortaFila.cortaFila.exception.RegraDeNegocioException;
import com.cortaFila.cortaFila.repository.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final BarbeiroService barbeiroService;
    private final UsuarioService usuarioService;
    private final BarbeiroServicoService barbeiroServicoService;

    @Transactional
    public AgendamentoResponseDTO criarAgendamento(AgendamentoRequestDTO dto){
        boolean agendamentoDuplicado = agendamentoRepository.existsByBarbeiroIdAndDataAndHorario(
                dto.idBarbeiro(),
                dto.data(),
                dto.horario()
        );

        if(agendamentoDuplicado){
            throw new RegraDeNegocioException("Já existe um agendamento para este barbeiro neste dia e horário!");
        }

        Barbeiro barbeiro = barbeiroService.buscarPorId(dto.idBarbeiro());

        Usuario usuario = null;
        if (dto.idUsuario() != null){
            usuario = usuarioService.buscarPorId(dto.idUsuario());
        }

        BarbeiroServico barbeiroServico = barbeiroServicoService.buscarBarbeiroServicoPorId(dto.idBarbeiroServico());

        Agendamento agendamento = new Agendamento();
        agendamento.setBarbeiro(barbeiro);
        agendamento.setUsuario(usuario);
        agendamento.setBarbeiroServico(barbeiroServico);
        agendamento.setData(dto.data());
        agendamento.setHorario(dto.horario());

        Agendamento salvo = agendamentoRepository.save(agendamento);

        return toResponseDTO(salvo);
    }

    public AgendamentoResponseDTO toResponseDTO(Agendamento agendamento){
        return new AgendamentoResponseDTO(
                agendamento.getId(),
                agendamento.getBarbeiro().getId(),
                agendamento.getBarbeiro().getUsuario().getNome(),
                agendamento.getUsuario() != null ? agendamento.getUsuario().getId() : null,
                agendamento.getUsuario() != null ? agendamento.getUsuario().getNome() : null,
                agendamento.getBarbeiroServico().getId(),
                agendamento.getBarbeiroServico().getTipoServico().getNome(),
                agendamento.getData(),
                agendamento.getHorario()
        );
    }
}

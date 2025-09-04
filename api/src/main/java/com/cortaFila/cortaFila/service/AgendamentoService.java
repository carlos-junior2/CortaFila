package com.cortaFila.cortaFila.service;

import com.cortaFila.cortaFila.data.dto.AgendamentoRequestDTO;
import com.cortaFila.cortaFila.data.dto.AgendamentoResponseDTO;
import com.cortaFila.cortaFila.data.model.*;
import com.cortaFila.cortaFila.exception.RegistroNaoEncontradoException;
import com.cortaFila.cortaFila.exception.RegraDeNegocioException;
import com.cortaFila.cortaFila.repository.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final BarbeiroService barbeiroService;
    private final UsuarioService usuarioService;
    private final BarbeiroServicoService barbeiroServicoService;
    private final HorarioTrabalhoService horarioTrabalhoService;

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

    public List<AgendamentoResponseDTO> listarAgendamentosPorBarbeiro(Long barbeiro, LocalDate data){
        Barbeiro b = barbeiroService.buscarPorId(barbeiro);
        List<Agendamento> agendamentos = agendamentoRepository.findByBarbeiroIdAndData(barbeiro, data);
        return agendamentos.stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public Page<AgendamentoResponseDTO> listarAgendamentosPorUsuario(Long idUsuario, Pageable pageable){
        Usuario usuario = usuarioService.buscarPorId(idUsuario);
        return agendamentoRepository.findByUsuario(usuario, pageable)
                .map(this::toResponseDTO);
    }

    public List<LocalTime> buscarHorariosDisponiveis(Long idBarbeiro, LocalDate data, Long idServico){
        // Pega o serviço para descobrir a duração
        BarbeiroServico servico = barbeiroServicoService.buscarBarbeiroServicoPorId(idServico);
        Integer duracaoMin = servico.getDuracaoMin();

        // Pega horários já ocupados do barbeiro no dia
        List<Agendamento> agendados = agendamentoRepository.findByBarbeiroIdAndData(idBarbeiro, data);

        // Jornada de trabalho do barbeiro
        DiaSemana dia = DiaSemana.fromDayOfWeek(data.getDayOfWeek());
        List<HorarioTrabalho> jornadas = horarioTrabalhoService.buscarHorariosPorBarbeiro(idBarbeiro, dia);
        if (jornadas.isEmpty()) {
            throw new RegistroNaoEncontradoException("Horários de trabalho não cadastrados para este dia.");
        }
        List<LocalTime> disponiveis = new ArrayList<>();

        for (HorarioTrabalho jornada : jornadas){
            LocalTime inicio = jornada.getHoraInicio();
            LocalTime fim = jornada.getHoraFim();
            LocalTime atual = inicio;

            while(!atual.plusMinutes(duracaoMin).isAfter(fim)){
                LocalTime inicioSlot = atual;
                LocalTime fimSlot = atual.plusMinutes(duracaoMin);

                boolean conflita = agendados.stream().anyMatch(ag ->{
                    LocalTime inicioAg = ag.getHorario();
                    LocalTime fimAg = inicioAg.plusMinutes(ag.getBarbeiroServico().getDuracaoMin());
                    return !(fimSlot.isBefore(inicioAg) || inicioSlot.isAfter(fimAg));
                });

                if (!conflita){
                    disponiveis.add(atual);
                }

                atual = atual.plusMinutes(30);
            }
        }
        return disponiveis;
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
                agendamento.getBarbeiroServico().getPreco(),
                agendamento.getData(),
                agendamento.getHorario(),
                agendamento.getHorario().plusMinutes(agendamento.getBarbeiroServico().getDuracaoMin())
        );
    }
}

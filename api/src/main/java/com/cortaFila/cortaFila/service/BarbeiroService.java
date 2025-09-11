package com.cortaFila.cortaFila.service;

import com.cortaFila.cortaFila.data.dto.BarbeiroRequestDTO;
import com.cortaFila.cortaFila.data.dto.BarbeiroResponseDTO;
import com.cortaFila.cortaFila.data.dto.HorarioTrabalhoDTO;
import com.cortaFila.cortaFila.data.model.Barbeiro;
import com.cortaFila.cortaFila.data.model.HorarioTrabalho;
import com.cortaFila.cortaFila.data.model.Usuario;
import com.cortaFila.cortaFila.exception.RegistroNaoEncontradoException;
import com.cortaFila.cortaFila.repository.BarbeariaRepository;
import com.cortaFila.cortaFila.repository.BarbeiroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BarbeiroService {

    private final BarbeiroRepository barbeiroRepository;
    private final UsuarioService usuarioService;
    private final BarbeariaRepository barbeariaRepository;
    private final HorarioTrabalhoService horarioTrabalhoService;

    public Page<BarbeiroResponseDTO> listar(Pageable pageable){
        return barbeiroRepository.findAll(pageable)
                .map(BarbeiroResponseDTO::new);
    }

    public Barbeiro buscarPorId(Long id){
        return barbeiroRepository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Barbeiro não encontrado!"));
    }

    @Transactional
    public BarbeiroResponseDTO salvar(BarbeiroRequestDTO dto){
        //Salva o usuário
        Usuario usuario = usuarioService.salvar(dto.usuario());

        //Busca barbearia
        var barbearia = barbeariaRepository.findById(dto.idBarbearia()).orElseThrow(() -> new RegistroNaoEncontradoException("Barbearia não encontrada!"));

        //Cria o barbeiro
        Barbeiro barbeiro = new Barbeiro();
        barbeiro.setUsuario(usuario);
        barbeiro.setBarbearia(barbearia);
        var salvo = barbeiroRepository.save(barbeiro);

        //Valida e converte os horários
        List<HorarioTrabalho> horarios = dto.horarios().stream()
                .map(horarioDTO -> {
                    LocalTime inicio = LocalTime.parse(horarioDTO.horaInicio());
                    LocalTime fim = LocalTime.parse(horarioDTO.horaFim());

                    if (!fim.isAfter(inicio)) {
                        throw new IllegalArgumentException(
                                "Hora final deve ser após a hora inicial (" + horarioDTO.diaSemana() + ")"
                        );
                    }

                    HorarioTrabalho ht = new HorarioTrabalho();
                    ht.setDiaSemana(horarioDTO.diaSemana());
                    ht.setHoraInicio(inicio);
                    ht.setHoraFim(fim);
                    ht.setBarbeiro(salvo);
                    return ht;
                })
                .toList();

        // Salva os horários
        horarioTrabalhoService.salvarHorarios(horarios);

        //Retorna a resposta
        return new BarbeiroResponseDTO(
                salvo.getId(),
                salvo.getUsuario().getId(),
                salvo.getUsuario().getNome(),
                salvo.getBarbearia().getId(),
                salvo.getBarbearia().getNome(),
                horarios.stream()
                        .map(HorarioTrabalhoDTO::fromEntity)
                        .toList()
        );
    }

}

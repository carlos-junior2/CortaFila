package com.cortaFila.cortaFila.data.dto;

import com.cortaFila.cortaFila.data.model.Barbeiro;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(name = "Barbeiro Resposta")
public record BarbeiroResponseDTO(
        Long id,
        Long idUsuario,
        String nomeUsuario,
        Long idBarbearia,
        String nomeBarbearia,
        List<HorarioTrabalhoDTO> horarios
){
    public BarbeiroResponseDTO (Barbeiro b){
        this(
                b.getId(),
                b.getUsuario().getId(),
                b.getUsuario().getNome(),
                b.getBarbearia().getId(),
                b.getBarbearia().getNome(),
                b.getHorarios().stream()
                        .map(HorarioTrabalhoDTO::new)
                        .toList()
                );
    }
}

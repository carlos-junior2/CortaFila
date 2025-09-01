package com.cortaFila.cortaFila.data.dto;

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
){}

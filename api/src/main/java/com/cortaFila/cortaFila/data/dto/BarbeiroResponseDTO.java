package com.cortaFila.cortaFila.data.dto;

import java.util.List;

public record BarbeiroResponseDTO(
        Long id,
        Long idUsuario,
        String nomeUsuario,
        Long idBarbearia,
        String nomeBarbearia,
        List<HorarioTrabalhoDTO> horarios
){}

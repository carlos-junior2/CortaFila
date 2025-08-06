package com.cortaFila.cortaFila.data.dto;

public record BarbeiroResponseDTO(
        Long id,
        Long idUsuario,
        String nomeUsuario,
        Long idBarbearia,
        String nomeBarbearia
){}

package com.cortaFila.cortaFila.data.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(name = "Barbearia com Barbeiros")
public record BarbeariaComBarbeiroDTO(
        Long id,
        String nome,
        String descricao,
        String email,
        String imagemPatch,
        List<EnderecoDTO> enderecos,
        List<BarbeiroResponseDTO> barbeiros
) {}
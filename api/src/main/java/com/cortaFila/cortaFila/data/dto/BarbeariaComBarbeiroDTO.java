package com.cortaFila.cortaFila.data.dto;

import java.util.List;

public record BarbeariaComBarbeiroDTO(
        Long id,
        String nome,
        String descricao,
        String email,
        String imagemPatch,
        List<EnderecoDTO> enderecos,
        List<BarbeiroResponseDTO> barbeiros
) {}
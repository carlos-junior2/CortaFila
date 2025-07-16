package com.cortaFila.cortaFila.data.dto;

import com.cortaFila.cortaFila.data.model.Role;

public record UsuarioRequestDTO(
        String nome,
        String username,
        String email,
        String senha,
        Role role
)
{}

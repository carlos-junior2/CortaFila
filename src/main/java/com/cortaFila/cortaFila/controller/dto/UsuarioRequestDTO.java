package com.cortaFila.cortaFila.controller.dto;

import com.cortaFila.cortaFila.model.Role;

public record UsuarioRequestDTO(
        String nome,
        String username,
        String email,
        String senha,
        Role role
)
{}

package com.cortaFila.cortaFila.controller.dto;

import com.cortaFila.cortaFila.model.Role;
import com.cortaFila.cortaFila.model.Usuario;

public record UsuarioResponseDTO(
        Integer id,
        String nome,
        String username,
        String email,
        Role role
)
{
    public UsuarioResponseDTO(Usuario usuario){
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getUsername(),
                usuario.getEmail(),
                usuario.getRole()
        );
    }
}

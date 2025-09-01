package com.cortaFila.cortaFila.data.dto;

import com.cortaFila.cortaFila.data.model.Role;
import com.cortaFila.cortaFila.data.model.Usuario;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Usuário Resposta")
public record UsuarioResponseDTO(
        Long id,
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

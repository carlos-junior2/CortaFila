package com.cortaFila.cortaFila.data.dto;

import com.cortaFila.cortaFila.data.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioRequestDTO(
        @NotBlank(message = "Nome não pode estar vazio")
        String nome,

        String username,

        @Email(message = "Email inválido")
        String email,

        @Size(min = 4, max = 50, message = "Senha deve ter entre 4 e 50 caracteres")
        String senha,

        Role role
)
{}

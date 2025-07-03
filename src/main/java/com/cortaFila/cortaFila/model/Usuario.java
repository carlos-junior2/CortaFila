package com.cortaFila.cortaFila.model;

import com.cortaFila.cortaFila.controller.dto.UsuarioRequestDTO;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name= "usuario", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
@Data
public class Usuario {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "username", length = 20, nullable = false, unique = true)
    private String username;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @Column(name = "senha", length = 100, nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 20, nullable = false)
    private Role role;

    public void toEntity(UsuarioRequestDTO dto){
        this.setNome(dto.nome());
        this.setUsername(dto.username());
        this.setEmail(dto.email());
        this.setSenha(dto.senha());
        this.setRole(dto.role());
    }
}

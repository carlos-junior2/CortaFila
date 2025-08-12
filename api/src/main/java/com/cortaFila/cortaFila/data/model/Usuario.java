package com.cortaFila.cortaFila.data.model;

import com.cortaFila.cortaFila.data.dto.UsuarioRequestDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name= "usuario", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
@Data
@Getter
@Setter
public class Usuario {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
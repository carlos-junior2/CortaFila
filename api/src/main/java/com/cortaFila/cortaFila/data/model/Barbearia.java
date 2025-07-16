package com.cortaFila.cortaFila.data.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "barbearias", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
@EntityListeners(AuditingEntityListener.class)
public class Barbearia {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @Column(name = "imagem_patch", length = 255)
    private String imagemPatch;

    @CreatedDate
    @Column(name = "criado_em")
    private LocalDateTime criadoEm;

    @LastModifiedDate
    @Column(name = "atualizado_em")
    private LocalDateTime atualizadoEm;

}

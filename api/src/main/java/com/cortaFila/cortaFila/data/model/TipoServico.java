package com.cortaFila.cortaFila.data.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tipo_servico")
@EntityListeners(AuditingEntityListener.class)
public class TipoServico {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "criado_em")
    @CreatedDate
    private LocalDateTime criadoEm;

    @Column(name = "atualizado_em")
    @LastModifiedDate
    private LocalDateTime atualizadoEm;
}

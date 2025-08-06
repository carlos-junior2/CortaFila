package com.cortaFila.cortaFila.data.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="barbeiros", uniqueConstraints = @UniqueConstraint(name= "uq_usuario_barbaria", columnNames = {"id_usuario", "id_barbearia"} ))
@EntityListeners(AuditingEntityListener.class)
public class Barbeiro {

    @Id
    @Column(name="id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario", nullable = false, foreignKey = @ForeignKey(name= "fk_barbeiro_usuario"))
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_barbearia", nullable = false, foreignKey = @ForeignKey(name= "fk_barbeiro_barbearia"))
    private Barbearia barbearia;

    @CreatedDate
    @Column(name = "criado_em")
    private LocalDateTime criadoEm;

    @LastModifiedDate
    @Column(name = "atualizado_em")
    private LocalDateTime atualizadoEm;
}

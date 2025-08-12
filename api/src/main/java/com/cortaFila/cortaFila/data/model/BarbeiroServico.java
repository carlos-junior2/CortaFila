package com.cortaFila.cortaFila.data.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "barbeiro_servico", uniqueConstraints = @UniqueConstraint(columnNames = {"barbeiro_id", "tipo_servico_id"}))
@EntityListeners(AuditingEntityListener.class)
public class BarbeiroServico {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_barbeiro", nullable = false)
    private Barbeiro barbeiro;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_tipo_servico", nullable = false)
    private TipoServico tipoServico;

    @Column(name = "preco", precision = 10, scale = 2)
    private BigDecimal preco;

    @Column(name = "duracao_min", nullable = false )
    private Integer duracaoMin;

    @CreatedDate
    @Column(name = "criado_em", nullable = false)
    private LocalDateTime criadoEm;

    @LastModifiedDate
    @Column(name = "atualizado_em", nullable = false)
    private LocalDateTime atualizadoEm;
}

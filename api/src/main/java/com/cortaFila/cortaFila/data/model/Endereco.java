package com.cortaFila.cortaFila.data.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "enderecos")
public class Endereco {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "barbearia_id")
    private Barbearia barbearia;

    @Column(name = "logradouro", length = 255)
    private String logradouro;

    @Column(name = "bairro", length = 100)
    private String bairro;

    @Column(name = "cidade", length = 100)
    private String cidade;

    @Column(name = "estado", length = 2)
    private String estado;

    @Column(name = "cep", length = 10)
    private String cep;

    @Column(name = "ponto_de_referencia", length = 255)
    private String pontoDeReferencia;

    @Column(name = "telefone", length = 20)
    private String telefone;

}

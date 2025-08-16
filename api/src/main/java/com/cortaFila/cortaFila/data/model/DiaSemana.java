package com.cortaFila.cortaFila.data.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum DiaSemana {
    DOMINGO("Domingo"),
    SEGUNDA("Segunda-feira"),
    TERCA("Terça-feira"),
    QUARTA("Quarta-feira"),
    QUINTA("Quinta-feira"),
    SEXTA("Sexta-feira"),
    SABADO("Sábado");

    private final String descricao;

    DiaSemana(String descricao) {
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }

    @JsonCreator
    public static DiaSemana fromDescricao(String descricao) {
        for (DiaSemana dia : values()) {
            if (dia.descricao.equalsIgnoreCase(descricao)) {
                return dia;
            }
        }
        throw new IllegalArgumentException("Dia inválido: " + descricao);
    }
}

package com.cortaFila.cortaFila.data.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public record AgendamentoResponseDTO(
        Long id,
        Long idBarbeiro,
        String barbeiroNome,
        Long idUsuario,
        String usuarioNome,
        Long idBarbeiroServico,
        String servicoNome,
        BigDecimal servicoPreco,
        LocalDate data,
        LocalTime inicio,
        LocalTime termino
){}

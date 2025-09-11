package com.cortaFila.cortaFila.data.dto;

import com.cortaFila.cortaFila.data.model.DiaSemana;
import com.cortaFila.cortaFila.data.model.HorarioTrabalho;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Schema(name = "Horário Trabalho")
public record HorarioTrabalhoDTO(
        @NotNull(message = "O dia da semana é obrigatório")
        DiaSemana diaSemana,

        @NotBlank(message = "A hora de início é obrigatória")
        @Pattern(regexp = "^([01]\\d|2[0-3]):[0-5]\\d$",
                message = "Hora de início deve estar no formato HH:mm")
        String horaInicio,

        @NotBlank(message = "A hora de fim é obrigatória")
        @Pattern(regexp = "^([01]\\d|2[0-3]):[0-5]\\d$",
                message = "Hora de fim deve estar no formato HH:mm")
        String horaFim
) {
        public HorarioTrabalhoDTO(HorarioTrabalho ht){
                this(
                        ht.getDiaSemana(),
                        ht.getHoraInicio().toString(),
                        ht.getHoraFim().toString()
                );
        }

        public static HorarioTrabalhoDTO fromEntity(HorarioTrabalho entity) {
                return new HorarioTrabalhoDTO(
                        entity.getDiaSemana(),
                        entity.getHoraInicio().toString(),
                        entity.getHoraFim().toString()
                );
        }

        public String getDiaSemanaDescricao() {
                return diaSemana.getDescricao();
        }
}
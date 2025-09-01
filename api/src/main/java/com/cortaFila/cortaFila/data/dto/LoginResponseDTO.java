package com.cortaFila.cortaFila.data.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Login Resposta")
public record LoginResponseDTO(String accessToken, String username) {
}

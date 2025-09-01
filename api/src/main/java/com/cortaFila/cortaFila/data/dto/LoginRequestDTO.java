package com.cortaFila.cortaFila.data.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Login")
public record LoginRequestDTO(String username, String password) {

}

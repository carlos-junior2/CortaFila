package com.cortaFila.cortaFila.infra.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Corta Fila",
                version = "v1"
        )
)
public class OpenApiConfiguration {
}

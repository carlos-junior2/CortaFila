package com.cortaFila.cortaFila.controller;

import com.cortaFila.cortaFila.data.dto.TipoServicoRequestDTO;
import com.cortaFila.cortaFila.data.dto.TipoServicoResponseDTO;
import com.cortaFila.cortaFila.service.TipoServicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/tipoServicos")
@RequiredArgsConstructor
@Tag(name = "Tipo Serviço")
public class TipoServicoController {

    private final TipoServicoService tipoServicoService;

    @PostMapping
    @Operation(summary = "Salvar" , description = "Cadastrar novo Tipo de Serviço")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<TipoServicoResponseDTO> salvar(@Valid @RequestBody TipoServicoRequestDTO dto){
        TipoServicoResponseDTO dtoSaida = tipoServicoService.salvar(dto);
        return ResponseEntity.ok(dtoSaida);
    }
}
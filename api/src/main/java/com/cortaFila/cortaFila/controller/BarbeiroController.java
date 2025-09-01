package com.cortaFila.cortaFila.controller;

import com.cortaFila.cortaFila.data.dto.BarbeiroRequestDTO;
import com.cortaFila.cortaFila.data.dto.BarbeiroResponseDTO;
import com.cortaFila.cortaFila.service.BarbeiroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/barbeiros")
@RequiredArgsConstructor
@Tag(name = "Barbeiros")
public class BarbeiroController {

    private final BarbeiroService barbeiroService;

    @PostMapping
    @Operation(summary = "Salvar", description = "Salvar um novo Barbeiro")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Barbeiro criado com sucesso"),
            @ApiResponse(responseCode = "409", description = "Barbeiro já cadastrado")
    })
    public ResponseEntity<BarbeiroResponseDTO> salvar(@Valid @RequestBody BarbeiroRequestDTO dto){
        return ResponseEntity.ok(barbeiroService.salvar(dto));
    }
}

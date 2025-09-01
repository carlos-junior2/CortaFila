package com.cortaFila.cortaFila.controller;

import com.cortaFila.cortaFila.data.dto.BarbeiroServicoRequestDTO;
import com.cortaFila.cortaFila.data.dto.BarbeiroServicoResponseDTO;
import com.cortaFila.cortaFila.data.dto.BarbeiroServicoTipoServicoDTO;
import com.cortaFila.cortaFila.service.BarbeiroServicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/barbeiroServicos")
@RequiredArgsConstructor
@Tag(name = "Barbeiro Serviços")
public class BarbeiroServicoController {

    private final BarbeiroServicoService barbeiroServicoService;

    @PostMapping
    @Operation(summary = "Salvar", description = "Salvar um novo Barbeiro Serviço")
    @ApiResponse(responseCode = "201", description = "Barbeiro Serviço criado com sucesso")
    public ResponseEntity<BarbeiroServicoResponseDTO> salvar(@Valid @RequestBody BarbeiroServicoRequestDTO dto){
        BarbeiroServicoResponseDTO barbeiroServicoResponseDTO = barbeiroServicoService.salvar(dto);
        return ResponseEntity.ok(barbeiroServicoResponseDTO);
    }

    @GetMapping("/{idBarbeiro}")
    @Operation(summary = "Listar por ID Barbeiro", description = "Listar Barbeiro Serviço por ID do Barbeiro")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK."),
            @ApiResponse(responseCode = "404", description = "Barbeiro Serviço não encontrado.")
    })
    public ResponseEntity<List<BarbeiroServicoTipoServicoDTO>> listarPorIdBarbeiro(@PathVariable Long idBarbeiro){
        return ResponseEntity.ok(barbeiroServicoService.buscarServicosPorIdBarbeiro(idBarbeiro));
    }


}

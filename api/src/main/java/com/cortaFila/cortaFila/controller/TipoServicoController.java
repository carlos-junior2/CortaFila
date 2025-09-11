package com.cortaFila.cortaFila.controller;

import com.cortaFila.cortaFila.data.dto.TipoServicoRequestDTO;
import com.cortaFila.cortaFila.data.dto.TipoServicoResponseDTO;
import com.cortaFila.cortaFila.service.TipoServicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping
    @Operation(summary = "Listar" , description = "Listar todos os Tipos de Serviços")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<Page<TipoServicoResponseDTO>> listar(
            @PageableDefault(size = 5, sort = "nome") Pageable pageable){
        Page<TipoServicoResponseDTO> resultado = tipoServicoService.listar(pageable);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Por ID", description = "Buscar Tipo de Serviço por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Tipo Serviço não encontrado!")
    })
    public ResponseEntity<TipoServicoResponseDTO> buscarPorId(@PathVariable Long id){
        TipoServicoResponseDTO dto = tipoServicoService.buscar(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar", description = "Atualizar Tipo de Serviço por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Tipo Serviço não encontrado!")
    })
    public ResponseEntity<TipoServicoResponseDTO> atualizar(@PathVariable Long id, @RequestBody TipoServicoRequestDTO dto){
        TipoServicoResponseDTO atualizado = tipoServicoService.atualizar(id, dto);
        return ResponseEntity.ok(atualizado);
    }
}
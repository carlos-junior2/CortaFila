package com.cortaFila.cortaFila.controller;

import com.cortaFila.cortaFila.data.dto.BarbeiroRequestDTO;
import com.cortaFila.cortaFila.data.dto.BarbeiroResponseDTO;
import com.cortaFila.cortaFila.data.model.Barbeiro;
import com.cortaFila.cortaFila.service.BarbeiroService;
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

    @GetMapping
    @Operation(summary = "Listar", description = "Listar todos os barbeiros")
    @ApiResponse(responseCode = "200", description = "OK.")
    public ResponseEntity<Page<BarbeiroResponseDTO>> listar(@PageableDefault(page = 0, size = 5, sort = "id") Pageable pageable){
        Page<BarbeiroResponseDTO> resultado = barbeiroService.listar(pageable);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar barbeiro por ID", description = "Buscar um barbeiro por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Barbeiro não encontrado!")
    })
    public ResponseEntity<BarbeiroResponseDTO> buscarPorId(@PathVariable Long id){
        Barbeiro barbeiro = barbeiroService.buscarPorId(id);
        BarbeiroResponseDTO dto = new BarbeiroResponseDTO(barbeiro);
        return ResponseEntity.ok(dto);
    }
    
}

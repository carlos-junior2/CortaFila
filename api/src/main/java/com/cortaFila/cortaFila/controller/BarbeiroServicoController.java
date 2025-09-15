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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping("/barbeiro/{idBarbeiro}")
    @Operation(summary = "Listar por ID Barbeiro", description = "Listar Barbeiro Serviço por ID do Barbeiro")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK."),
            @ApiResponse(responseCode = "404", description = "Barbeiro Serviço não encontrado."),
            @ApiResponse(responseCode = "404", description = "Barbeiro não encontrado.")
    })
    public ResponseEntity<List<BarbeiroServicoTipoServicoDTO>> listarPorIdBarbeiro(@PathVariable Long idBarbeiro){
        return ResponseEntity.ok(barbeiroServicoService.buscarServicosPorIdBarbeiro(idBarbeiro));
    }

    @GetMapping
    @Operation(summary = "Listar", description = "Listar todos os Barbeiro Servicos")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<Page<BarbeiroServicoTipoServicoDTO>> listar(
            @PageableDefault(size = 5, sort = "tipoServico.nome") Pageable pageable){
        Page<BarbeiroServicoTipoServicoDTO> resultado = barbeiroServicoService.listar(pageable);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar por ID", description = "Buscar Barbeiro Serviço por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK."),
            @ApiResponse(responseCode = "404", description = "Barbeiro Serviço não encontrado.")
    })
    public ResponseEntity<BarbeiroServicoTipoServicoDTO> buscarPorId(@PathVariable Long id){
        BarbeiroServicoTipoServicoDTO dto = barbeiroServicoService.buscarPorId(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "atualizar", description = "Atualizar um Barbeiro Serviço por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK."),
            @ApiResponse(responseCode = "404", description = "Barbeiro Serviço não encontrado.")
    })
    public ResponseEntity<BarbeiroServicoTipoServicoDTO> atualizar(@PathVariable Long id, @RequestBody BarbeiroServicoRequestDTO dto){
        BarbeiroServicoTipoServicoDTO atualizado = barbeiroServicoService.atualizar(id, dto);
        return ResponseEntity.ok(atualizado);
    }
}

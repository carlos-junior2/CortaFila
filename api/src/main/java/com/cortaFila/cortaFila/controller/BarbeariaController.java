package com.cortaFila.cortaFila.controller;

import com.cortaFila.cortaFila.data.dto.BarbeariaComBarbeiroDTO;
import com.cortaFila.cortaFila.data.dto.BarbeariaRequestDTO;
import com.cortaFila.cortaFila.data.dto.BarbeariaResponseDTO;
import com.cortaFila.cortaFila.service.BarbeariaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

@RestController
@RequestMapping("/barbearias")
@RequiredArgsConstructor
@Tag(name = "Barbearias")
public class BarbeariaController {

    private final BarbeariaService barbeariaService;

    @PostMapping
    @Operation(summary = "Salvar" , description = "Cadastrar nova barbearia")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cadastrado com sucesso."),
            @ApiResponse(responseCode = "409", description = "Barbearia já cadastrada.")
    })
    public ResponseEntity<BarbeariaResponseDTO> criarBarbearia(@Valid @RequestBody BarbeariaRequestDTO barbeariaRequestDTO){
        BarbeariaResponseDTO dto = barbeariaService.salvar(barbeariaRequestDTO);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}/imagem")
    @Operation(summary = "Atualizar", description = "Atualizar uma barbearia existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Atualizado com sucesso."),
            @ApiResponse(responseCode = "409", description = "Barbearia já cadastrada.")
    })
    public ResponseEntity<String> atualizarImagem(@PathVariable Long id,
                                                  @RequestParam("file") MultipartFile file) {
        try {
            String path = barbeariaService.atualizarImagemPerfil(id, file);
            return ResponseEntity.ok("Imagem atualizada: " + path);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao atualizar imagem: " + e.getMessage());
        }
    }

    @GetMapping("/{id}/imagem")
    @Operation(summary = "Buscar Imagem", description = "Buscar path da imagem de uma barbearia")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK."),
            @ApiResponse(responseCode = "404", description = "Barbearia não encontrada.")
    })
    public ResponseEntity<String> getImagem(@PathVariable Long id) {
        String imagemPath = barbeariaService.obterPathImagem(id);
        return ResponseEntity.ok(imagemPath);
    }

    @Operation(summary = "Listar", description = "Listar todas as barbearias")
    @ApiResponse(responseCode = "200", description = "OK.")
    @GetMapping
    public ResponseEntity<Page<BarbeariaResponseDTO>> listarTodas(
            @RequestParam(required = false) String cidade,
            @PageableDefault(page = 0, size = 5, sort = "nome") Pageable pageable) {

        Page<BarbeariaResponseDTO> resultado;
        if (cidade != null && !cidade.isEmpty()) {
            resultado = barbeariaService.listarPorCidade(cidade, pageable);
        } else{
            resultado = barbeariaService.listar(pageable);
        }

        return ResponseEntity.ok(resultado);
    }

    @Operation(summary = "Listar com Barbeiros", description = "Listar todas as barbearias com seus barbeiros")
    @ApiResponse(responseCode = "200", description = "OK.")
    @GetMapping("/barbeiros")
    public ResponseEntity<List<BarbeariaComBarbeiroDTO>> listarTodasComBarbeiros(){
        var lista = barbeariaService.listarComBarbeiros();
        return ResponseEntity.ok(lista);
    }

    @Operation(summary = "Buscar barbearia por ID", description = "Listar barbearia por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK."),
            @ApiResponse(responseCode = "404", description = "Barbearia não encontrada.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<BarbeariaComBarbeiroDTO> buscarBarbeariaPorId(@PathVariable Long id){
        BarbeariaComBarbeiroDTO dto = barbeariaService.buscarBarbeariaPorId(id);
        return ResponseEntity.ok(dto);
    }
}

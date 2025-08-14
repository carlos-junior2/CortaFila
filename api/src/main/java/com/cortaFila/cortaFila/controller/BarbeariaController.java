package com.cortaFila.cortaFila.controller;

import com.cortaFila.cortaFila.data.dto.BarbeariaComBarbeiroDTO;
import com.cortaFila.cortaFila.data.dto.BarbeariaRequestDTO;
import com.cortaFila.cortaFila.data.dto.BarbeariaResponseDTO;
import com.cortaFila.cortaFila.service.BarbeariaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/barbearias")
@RequiredArgsConstructor
public class BarbeariaController {

    private final BarbeariaService barbeariaService;

    @PostMapping
    public ResponseEntity<BarbeariaResponseDTO> criarBarbearia(@Valid @RequestBody BarbeariaRequestDTO barbeariaRequestDTO){
        BarbeariaResponseDTO dto = barbeariaService.salvar(barbeariaRequestDTO);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}/imagem")
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
    public ResponseEntity<String> getImagem(@PathVariable Long id) {
        String imagemPath = barbeariaService.obterPathImagem(id);
        return ResponseEntity.ok(imagemPath);
    }

    @GetMapping
    public ResponseEntity<List<BarbeariaResponseDTO>> listarTodas() {
        List<BarbeariaResponseDTO> barbearias = barbeariaService.listar();
        return ResponseEntity.ok(barbearias);
    }

    @GetMapping("/barbeiros")
    public ResponseEntity<List<BarbeariaComBarbeiroDTO>> listarTodasComBarbeiros(){
        var lista = barbeariaService.listarComBarbeiros();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BarbeariaComBarbeiroDTO> buscarBarbeariaPorId(@PathVariable Long id){
        BarbeariaComBarbeiroDTO dto = barbeariaService.buscarBarbeariaPorId(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/teste-publico")
    public String teste() {
        return "acesso liberado";
    }
}

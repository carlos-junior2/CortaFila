package com.cortaFila.cortaFila.controller;

import com.cortaFila.cortaFila.data.dto.BarbeariaComBarbeiroDTO;
import com.cortaFila.cortaFila.data.dto.BarbeariaRequestDTO;
import com.cortaFila.cortaFila.data.dto.BarbeariaResponseDTO;
import com.cortaFila.cortaFila.data.model.Barbearia;
import com.cortaFila.cortaFila.service.BarbeariaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/barbearias")
@RequiredArgsConstructor
public class BarbeariaController {

    private final BarbeariaService barbeariaService;

    @PostMapping
    public ResponseEntity<Barbearia> criarBarbearia(@RequestBody BarbeariaRequestDTO barbeariaRequestDTO){
        barbeariaService.salvar(barbeariaRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
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

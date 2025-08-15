package com.cortaFila.cortaFila.controller;

import com.cortaFila.cortaFila.data.dto.BarbeiroServicoRequestDTO;
import com.cortaFila.cortaFila.data.dto.BarbeiroServicoResponseDTO;
import com.cortaFila.cortaFila.data.dto.BarbeiroServicoTipoServicoDTO;
import com.cortaFila.cortaFila.service.BarbeiroServicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/barbeiroServicos")
@RequiredArgsConstructor
public class BarbeiroServicoController {

    private final BarbeiroServicoService barbeiroServicoService;

    @PostMapping
    public ResponseEntity<BarbeiroServicoResponseDTO> salvar(@Valid @RequestBody BarbeiroServicoRequestDTO dto){
        BarbeiroServicoResponseDTO barbeiroServicoResponseDTO = barbeiroServicoService.salvar(dto);
        return ResponseEntity.ok(barbeiroServicoResponseDTO);
    }

    @GetMapping("/{idBarbeiro}")
    public ResponseEntity<List<BarbeiroServicoTipoServicoDTO>> listarPorIdBarbeiro(@PathVariable Long idBarbeiro){
        return ResponseEntity.ok(barbeiroServicoService.buscarServicosPorIdBarbeiro(idBarbeiro));
    }


}

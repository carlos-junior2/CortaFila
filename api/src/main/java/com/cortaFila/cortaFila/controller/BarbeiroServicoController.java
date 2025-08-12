package com.cortaFila.cortaFila.controller;

import com.cortaFila.cortaFila.data.dto.BarbeiroServicoRequestDTO;
import com.cortaFila.cortaFila.data.dto.BarbeiroServicoResponseDTO;
import com.cortaFila.cortaFila.service.BarbeiroServicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/barbeiroServicos")
@RequiredArgsConstructor
public class BarbeiroServicoController {

    private final BarbeiroServicoService barbeiroServicoService;

    @PostMapping
    public ResponseEntity<BarbeiroServicoResponseDTO> salvar(@RequestBody BarbeiroServicoRequestDTO dto){
        BarbeiroServicoResponseDTO barbeiroServicoResponseDTO = barbeiroServicoService.salvar(dto);
        return ResponseEntity.ok(barbeiroServicoResponseDTO);
    }
}

package com.cortaFila.cortaFila.controller;

import com.cortaFila.cortaFila.data.dto.BarbeiroRequestDTO;
import com.cortaFila.cortaFila.data.dto.BarbeiroResponseDTO;
import com.cortaFila.cortaFila.service.BarbeiroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/barbeiros")
@RequiredArgsConstructor
public class BarbeiroController {

    private final BarbeiroService barbeiroService;

    @PostMapping
    public ResponseEntity<BarbeiroResponseDTO> salvar(@RequestBody BarbeiroRequestDTO dto){
        return ResponseEntity.ok(barbeiroService.salvar(dto));
    }
}

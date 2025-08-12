package com.cortaFila.cortaFila.controller;

import com.cortaFila.cortaFila.data.dto.TipoServicoRequestDTO;
import com.cortaFila.cortaFila.data.dto.TipoServicoResponseDTO;
import com.cortaFila.cortaFila.service.TipoServicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tipoServicos")
@RequiredArgsConstructor
public class TipoServicoController {

    private final TipoServicoService tipoServicoService;

    @PostMapping
    public ResponseEntity<TipoServicoResponseDTO> salvar(@RequestBody TipoServicoRequestDTO dto){
        TipoServicoResponseDTO dtoSaida = tipoServicoService.salvar(dto);
        return ResponseEntity.ok(dtoSaida);
    }
}
package com.cortaFila.cortaFila.controller;

import com.cortaFila.cortaFila.data.dto.BarbeariaRequestDTO;
import com.cortaFila.cortaFila.data.model.Barbearia;
import com.cortaFila.cortaFila.service.BarbeariaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

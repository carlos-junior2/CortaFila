package com.cortaFila.cortaFila.controller;

import com.cortaFila.cortaFila.data.dto.EnderecoDTO;
import com.cortaFila.cortaFila.data.model.Barbearia;
import com.cortaFila.cortaFila.data.model.Endereco;
import com.cortaFila.cortaFila.exception.RegistroNaoEncontradoException;
import com.cortaFila.cortaFila.service.BarbeariaService;
import com.cortaFila.cortaFila.service.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private final EnderecoService enderecoService;
    private final BarbeariaService barbeariaService;

    @PostMapping("/{idBarbearia}")
    public ResponseEntity<EnderecoDTO> salvar(@PathVariable Long idBarbearia, @RequestBody EnderecoDTO dto){
        Optional<Barbearia> optionalBarbearia = barbeariaService.buscarPorId(idBarbearia);
        if (optionalBarbearia.isEmpty()) {
            throw new RegistroNaoEncontradoException("Barbearia não encontrada na base de dados!");
        }
        Barbearia barbearia = optionalBarbearia.get();
        Endereco endereco = dto.toEntity();
        endereco.setBarbearia(barbearia);

        Endereco enderecoSalvo = enderecoService.salvar(endereco);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

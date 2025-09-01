package com.cortaFila.cortaFila.controller;

import com.cortaFila.cortaFila.data.dto.EnderecoDTO;
import com.cortaFila.cortaFila.data.model.Barbearia;
import com.cortaFila.cortaFila.data.model.Endereco;
import com.cortaFila.cortaFila.exception.RegistroNaoEncontradoException;
import com.cortaFila.cortaFila.service.BarbeariaService;
import com.cortaFila.cortaFila.service.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/enderecos")
@Tag(name = "Endereços")
public class EnderecoController {

    private final EnderecoService enderecoService;
    private final BarbeariaService barbeariaService;

    @PostMapping("/{idBarbearia}")
    @Operation(summary = "Salvar", description = "Salvar um novo endereço")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Endereço criado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Barbearia não encontrada")
    })
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

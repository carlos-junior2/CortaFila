package com.cortaFila.cortaFila.controller;


import com.cortaFila.cortaFila.controller.dto.UsuarioRequestDTO;
import com.cortaFila.cortaFila.controller.dto.UsuarioResponseDTO;
import com.cortaFila.cortaFila.model.Usuario;
import com.cortaFila.cortaFila.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> salvarUsuario(@RequestBody UsuarioRequestDTO dto){
        Usuario usuario = usuarioService.salvar(dto);
        UsuarioResponseDTO responseDTO = new UsuarioResponseDTO(usuario);
        URI uri = URI.create("/" + responseDTO.id());
        return ResponseEntity.created(uri).body(responseDTO);
    }
}

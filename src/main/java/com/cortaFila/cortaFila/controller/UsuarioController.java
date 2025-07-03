package com.cortaFila.cortaFila.controller;


import com.cortaFila.cortaFila.controller.dto.UsuarioRequestDTO;
import com.cortaFila.cortaFila.controller.dto.UsuarioResponseDTO;
import com.cortaFila.cortaFila.model.Usuario;
import com.cortaFila.cortaFila.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

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

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizarUsuario(@PathVariable("id") Integer id, @RequestBody UsuarioRequestDTO usuarioRequestDTO){
        Optional<Usuario> usuarioOptional = usuarioService.buscarPorId(id);

        if (usuarioOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Usuario usuario = usuarioOptional.get();
        usuario.setNome(usuarioRequestDTO.nome());
        usuario.setEmail(usuarioRequestDTO.email());
        usuario.setSenha(usuarioRequestDTO.senha());
        usuario.setUsername(usuarioRequestDTO.username());
        usuario.setRole(usuarioRequestDTO.role());

        usuarioService.atualizar(usuario);
        return ResponseEntity.noContent().build();
    }

}

package com.cortaFila.cortaFila.controller;


import com.cortaFila.cortaFila.controller.dto.UsuarioRequestDTO;
import com.cortaFila.cortaFila.controller.dto.UsuarioResponseDTO;
import com.cortaFila.cortaFila.model.Usuario;
import com.cortaFila.cortaFila.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    //Método para cadastrar um novo cliente
    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> salvarUsuario(@RequestBody UsuarioRequestDTO dto){
        Usuario usuario = usuarioService.salvar(dto);
        UsuarioResponseDTO responseDTO = new UsuarioResponseDTO(usuario);
        URI uri = URI.create("/" + responseDTO.id());
        return ResponseEntity.created(uri).body(responseDTO);
    }

    //Método para atualizar um cliente via ID
    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarUsuario(@PathVariable("id") Integer id, @RequestBody UsuarioRequestDTO usuarioRequestDTO){
        Usuario usuario = usuarioService.buscarPorId(id);

        usuario.setNome(usuarioRequestDTO.nome());
        usuario.setEmail(usuarioRequestDTO.email());
        usuario.setSenha(usuarioRequestDTO.senha());
        usuario.setUsername(usuarioRequestDTO.username());
        usuario.setRole(usuarioRequestDTO.role());

        usuarioService.atualizar(usuario);
        return ResponseEntity.noContent().build();
    }

    //Método para excluir um cliente via ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable("id") Integer id){
        Usuario usuario = usuarioService.buscarPorId(id);
        usuarioService.excluir(usuario);
        return ResponseEntity.ok().build();
    }
}

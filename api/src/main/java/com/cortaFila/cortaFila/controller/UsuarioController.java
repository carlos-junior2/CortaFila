package com.cortaFila.cortaFila.controller;


import com.cortaFila.cortaFila.data.dto.UsuarioRequestDTO;
import com.cortaFila.cortaFila.data.dto.UsuarioResponseDTO;
import com.cortaFila.cortaFila.data.model.Usuario;
import com.cortaFila.cortaFila.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuarios")
@Tag(name = "Usuários")
public class UsuarioController {

    private final UsuarioService usuarioService;

    //Método para cadastrar um novo cliente
    @PostMapping
    @Operation(summary = "Salvar" , description = "Cadastrar novo usuário")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Cadastrado com sucesso."),
        @ApiResponse(responseCode = "409", description = "Usuário já cadastrado.")
    })
    public ResponseEntity<UsuarioResponseDTO> salvarUsuario(@Valid @RequestBody UsuarioRequestDTO dto){
        Usuario usuario = usuarioService.salvar(dto);
        UsuarioResponseDTO responseDTO = new UsuarioResponseDTO(usuario);
        URI uri = URI.create("/" + responseDTO.id());
        return ResponseEntity.created(uri).body(responseDTO);
    }

    //Método para atualizar um cliente via ID
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar", description = "Atualizar um usuário existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Atualizado com sucesso."),
            @ApiResponse(responseCode = "409", description = "Usuário já cadastrado.")
    })
    public ResponseEntity<Void> atualizarUsuario(@Valid @PathVariable("id") Long id, @RequestBody UsuarioRequestDTO usuarioRequestDTO){
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
    @Operation(summary = "Excluir", description = "Excluir um usuário existente")
    @ApiResponse(responseCode = "200", description = "Excluído com sucesso.")
    public ResponseEntity<Void> deletarUsuario(@PathVariable("id") Long id){
        Usuario usuario = usuarioService.buscarPorId(id);
        usuarioService.excluir(usuario);
        return ResponseEntity.ok().build();
    }
}

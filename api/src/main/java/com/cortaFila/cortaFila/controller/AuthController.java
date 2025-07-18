package com.cortaFila.cortaFila.controller;

import com.cortaFila.cortaFila.data.dto.LoginRequestDTO;
import com.cortaFila.cortaFila.data.dto.LoginResponseDTO;
import com.cortaFila.cortaFila.data.dto.UsuarioRequestDTO;
import com.cortaFila.cortaFila.data.model.Usuario;
import com.cortaFila.cortaFila.infra.security.TokenService;
import com.cortaFila.cortaFila.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.RuntimeErrorException;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private final UsuarioRepository usuarioRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequest) {
        Optional<Usuario> optionalUsuario = this.usuarioRepository.findByUsername(loginRequest.username());

        Usuario usuario = optionalUsuario.orElseThrow(() -> new RuntimeErrorException(new Error("User not found")));

        if (passwordEncoder.matches(loginRequest.password(), usuario.getSenha())) {
            String token = this.tokenService.generateToken(usuario);

            return ResponseEntity.ok(new LoginResponseDTO(token, usuario.getUsername()));
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        Optional<Usuario> usuario = this.usuarioRepository.findByEmail(usuarioRequestDTO.email());

        if (usuario.isEmpty()) {
            Usuario newUser = new Usuario();
            newUser.setUsername(usuarioRequestDTO.username());
            newUser.setEmail(usuarioRequestDTO.email());
            newUser.setNome(usuarioRequestDTO.nome());
            newUser.setSenha(passwordEncoder.encode(usuarioRequestDTO.senha()));
            newUser.setRole(usuarioRequestDTO.role());

            this.usuarioRepository.save(newUser);

            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(new LoginResponseDTO(token, newUser.getUsername()));
        }

        return ResponseEntity.badRequest().build();
    }

}

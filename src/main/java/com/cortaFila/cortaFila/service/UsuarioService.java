package com.cortaFila.cortaFila.service;

import com.cortaFila.cortaFila.controller.dto.UsuarioRequestDTO;
import com.cortaFila.cortaFila.exception.RegistroDuplicadoException;
import com.cortaFila.cortaFila.model.Usuario;
import com.cortaFila.cortaFila.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public Usuario salvar(UsuarioRequestDTO requestDTO){
        if (usuarioRepository.existsByEmail(requestDTO.email())){
            throw new RegistroDuplicadoException("Já existe um usuário com este email!");
        }

        if (usuarioRepository.existsByUsername(requestDTO.username())){
            throw new RegistroDuplicadoException("Já existe um usuário com este username!");
        }
        String senhaCriptografada = passwordEncoder.encode(requestDTO.senha());
        Usuario usuario = new Usuario();
        usuario.toEntity(requestDTO);
        usuario.setSenha(senhaCriptografada);
        return usuarioRepository.save(usuario);
    }
}

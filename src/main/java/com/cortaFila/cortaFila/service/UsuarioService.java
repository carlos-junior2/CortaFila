package com.cortaFila.cortaFila.service;

import com.cortaFila.cortaFila.controller.dto.UsuarioRequestDTO;
import com.cortaFila.cortaFila.exception.RegistroDuplicadoException;
import com.cortaFila.cortaFila.model.Usuario;
import com.cortaFila.cortaFila.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public Optional<Usuario> buscarPorId(Integer id){
        return usuarioRepository.findById(id);
    }

    public void atualizar(Usuario usuario){
        if(usuario.getId() == null){
            throw new IllegalArgumentException("Usuário não encontrado");
        }
        if (usuarioRepository.existsByEmail(usuario.getEmail())){
            throw new RegistroDuplicadoException("Já existe um usuário com este email!");
        }

        if (usuarioRepository.existsByUsername(usuario.getUsername())){
            throw new RegistroDuplicadoException("Já existe um usuário com este username!");
        }
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        usuarioRepository.save(usuario);
    }
}

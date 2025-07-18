package com.cortaFila.cortaFila.service;

import com.cortaFila.cortaFila.data.dto.UsuarioRequestDTO;
import com.cortaFila.cortaFila.exception.RegistroDuplicadoException;
import com.cortaFila.cortaFila.exception.RegistroNaoEncontradoException;
import com.cortaFila.cortaFila.data.model.Usuario;
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
        validarDuplicidade(requestDTO.email(), requestDTO.username(), null);
        Usuario usuario = new Usuario();
        usuario.toEntity(requestDTO);
        usuario.setSenha(passwordEncoder.encode(requestDTO.senha()));
        return usuarioRepository.save(usuario);
    }

    public Usuario buscarPorId(Long id){
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Usuário não encontrado!"));
    }

    public void atualizar(Usuario usuario){
        if(usuario.getId() == null){
            throw new RegistroNaoEncontradoException("Usuário não encontrado!");
        }
        validarDuplicidade(usuario.getEmail(), usuario.getUsername(), usuario.getId());
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        usuarioRepository.save(usuario);
    }

    public void excluir(Usuario usuario){
        if (usuario.getId() == null){
            throw new RegistroNaoEncontradoException("Usuário não encontrado!");
        }
        usuarioRepository.deleteById(usuario.getId());
    }

    private void validarDuplicidade(String email, String username, Long idIgnorado) {
        if (usuarioRepository.existsByEmailAndIdNot(email, idIgnorado)) {
            throw new RegistroDuplicadoException("Já existe um usuário com este email!");
        }
        if (usuarioRepository.existsByUsernameAndIdNot(username, idIgnorado)) {
            throw new RegistroDuplicadoException("Já existe um usuário com este username!");
        }
    }
}

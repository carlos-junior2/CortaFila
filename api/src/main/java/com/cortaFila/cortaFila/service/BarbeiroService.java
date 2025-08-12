package com.cortaFila.cortaFila.service;

import com.cortaFila.cortaFila.data.dto.BarbeiroRequestDTO;
import com.cortaFila.cortaFila.data.dto.BarbeiroResponseDTO;
import com.cortaFila.cortaFila.data.model.Barbeiro;
import com.cortaFila.cortaFila.data.model.Usuario;
import com.cortaFila.cortaFila.exception.RegistroNaoEncontradoException;
import com.cortaFila.cortaFila.repository.BarbeariaRepository;
import com.cortaFila.cortaFila.repository.BarbeiroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;

@Service
@RequiredArgsConstructor
public class BarbeiroService {

    private final BarbeiroRepository barbeiroRepository;
    private final UsuarioService usuarioService;
    private final BarbeariaRepository barbeariaRepository;

    public Barbeiro buscarPorId(Long id){
        return barbeiroRepository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Barbeiro não encontrado!"));
    }

    @Transactional
    public BarbeiroResponseDTO salvar(BarbeiroRequestDTO dto){
        Usuario usuario = usuarioService.salvar(dto.usuario());

        //Busca barbearia
        var barbearia = barbeariaRepository.findById(dto.idBarbearia()).orElseThrow(() -> new RegistroNaoEncontradoException("Barbearia não encontrada!"));

        //Cria o barbeiro
        Barbeiro barbeiro = new Barbeiro();
        barbeiro.setUsuario(usuario);
        barbeiro.setBarbearia(barbearia);

        var salvo = barbeiroRepository.save(barbeiro);

        return new BarbeiroResponseDTO(
                salvo.getId(),
                salvo.getUsuario().getId(),
                salvo.getUsuario().getNome(),
                salvo.getBarbearia().getId(),
                salvo.getBarbearia().getNome()
        );
    }
}

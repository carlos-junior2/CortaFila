package com.cortaFila.cortaFila.service;

import com.cortaFila.cortaFila.data.dto.*;
import com.cortaFila.cortaFila.data.model.Barbearia;
import com.cortaFila.cortaFila.data.model.Endereco;
import com.cortaFila.cortaFila.exception.RegistroDuplicadoException;
import com.cortaFila.cortaFila.exception.RegistroNaoEncontradoException;
import com.cortaFila.cortaFila.repository.BarbeariaRepository;
import com.cortaFila.cortaFila.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BarbeariaService {

    private final BarbeariaRepository barbeariaRepository;
    private final EnderecoRepository enderecoRepository;

    public BarbeariaResponseDTO salvar(BarbeariaRequestDTO dto) {
        validarDuplicidade(dto.email(), null);
        Barbearia barbearia = dto.toEntity();
        Barbearia barbeariaSalva = barbeariaRepository.save(barbearia);

        Endereco endereco = barbeariaSalva.getEnderecos().stream().findFirst().orElse(null);

        return new BarbeariaResponseDTO(barbeariaSalva, endereco);
    }

    public String atualizarImagemPerfil(Long id, MultipartFile file) throws IOException {
        Barbearia barbearia = barbeariaRepository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Barbearia não encontrada"));

        // Deletar imagem antiga se existir
        if (barbearia.getImagemPatch() != null) {
            File antiga = new File("."+barbearia.getImagemPatch()); // adiciona "." para ser relativo à raiz do projeto
            if (antiga.exists()) {
                antiga.delete();
            }
        }

        // Pasta física para salvar a imagem
        String pastaFisica = "uploads/barbearias/" + id;
        File dir = new File(pastaFisica);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // Nome do novo arquivo
        String nomeArquivo = "perfil_" + id + "_" + file.getOriginalFilename();

        // Caminho físico onde será salvo
        Path caminhoFisico = Paths.get(pastaFisica, nomeArquivo);
        Files.write(caminhoFisico, file.getBytes());

        // Caminho relativo que será salvo no banco e usado no front
        String caminhoRelativo = "/uploads/barbearias/" + id + "/" + nomeArquivo;

        // Atualiza no banco
        barbearia.setImagemPatch(caminhoRelativo);
        barbeariaRepository.save(barbearia);

        return caminhoRelativo;
    }

    public String obterPathImagem(Long id) {
        Barbearia barbearia = barbeariaRepository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Barbearia não encontrada"));

        String imagemPatch = barbearia.getImagemPatch();

        if (imagemPatch == null || imagemPatch.isBlank()) {
            // Caminho base da imagem padrão
            return "/uploads/default/perfil.png";
        }

        return imagemPatch;
    }


    public Optional<Barbearia> buscarPorId(Long id){
        return barbeariaRepository.findById(id);
    }

    public BarbeariaComBarbeiroDTO buscarBarbeariaPorId(Long id){
        Barbearia barbearia = barbeariaRepository.findByIdComBarbeiros(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Barbearia não encontrada!"));

        return new BarbeariaComBarbeiroDTO(
                barbearia.getId(),
                barbearia.getNome(),
                barbearia.getDescricao(),
                barbearia.getEmail(),
                barbearia.getImagemPatch(),
                barbearia.getEnderecos().stream()
                        .map(e -> new EnderecoDTO(
                                e.getLogradouro(),
                                e.getBairro(),
                                e.getCidade(),
                                e.getEstado(),
                                e.getCep(),
                                e.getPontoDeReferencia(),
                                e.getTelefone()
                        ))
                        .toList(),

                barbearia.getBarbeiros().stream()
                        .map(b -> new BarbeiroResponseDTO(
                                b.getId(),
                                b.getUsuario().getId(),
                                b.getUsuario().getNome(),
                                b.getBarbearia().getId(),
                                b.getBarbearia().getNome(),
                                b.getHorarios().stream()
                                        .map(HorarioTrabalhoDTO::fromEntity)
                                        .toList()
                        ))
                        .toList()
        );
    }

    public List<BarbeariaResponseDTO> listar(){
        List<Barbearia> barbearias = barbeariaRepository.findAllComEnderecos();

        return barbearias.stream()
                .map(barbearia -> {
                    List<EnderecoDTO> enderecosDTO = barbearia.getEnderecos().stream()
                            .map(EnderecoDTO::fromEntity)
                            .collect(Collectors.toList());

                    return new BarbeariaResponseDTO(
                            barbearia.getId(),
                            barbearia.getNome(),
                            barbearia.getDescricao(),
                            barbearia.getEmail(),
                            barbearia.getImagemPatch(),
                            enderecosDTO
                    );
                }).collect(Collectors.toList());
    }

    public List<BarbeariaComBarbeiroDTO> listarComBarbeiros() {
        List<Barbearia> barbearias = barbeariaRepository.findAllComBarbeiros();

        return barbearias.stream()
                .map(b -> new BarbeariaComBarbeiroDTO(
                        b.getId(),
                        b.getNome(),
                        b.getDescricao(),
                        b.getEmail(),
                        b.getImagemPatch(),
                        // Mapear lista de Endereco para lista de EnderecoDTO
                        b.getEnderecos().stream()
                                .map(e -> new EnderecoDTO(
                                        e.getLogradouro(),
                                        e.getBairro(),
                                        e.getCidade(),
                                        e.getEstado(),
                                        e.getCep(),
                                        e.getPontoDeReferencia(),
                                        e.getTelefone()
                                ))
                                .toList(),
                        // Mapear lista de Barbeiro para lista de BarbeiroResponseDTO
                        b.getBarbeiros().stream()
                                .map(barbeiro -> new BarbeiroResponseDTO(
                                        barbeiro.getId(),
                                        barbeiro.getUsuario().getId(),
                                        barbeiro.getUsuario().getNome(),
                                        barbeiro.getBarbearia().getId(),
                                        barbeiro.getBarbearia().getNome(),
                                        barbeiro.getHorarios().stream()
                                                .map(HorarioTrabalhoDTO::fromEntity)
                                                .toList()
                                ))
                                .toList()
                ))
                .toList();
    }

    private void validarDuplicidade(String email, Long id) {
        if (barbeariaRepository.existsByEmailAndIdNot(email, id)) {
            throw new RegistroDuplicadoException("Já existe uma barbearia cadastrada com este email!");
        }
    }

}

package com.example.Tema1.service;

import com.example.Tema1.DTO.AcessorioDTO;
import com.example.Tema1.DTO.VeiculoDTO;
import com.example.Tema1.adapter.AcessorioAdapter;
import com.example.Tema1.adapter.VeiculoAdapter;
import com.example.Tema1.entity.Acessorio;
import com.example.Tema1.entity.Veiculo;
import com.example.Tema1.repository.VeiculoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final VeiculoAdapter veiculoAdapter;

    public Veiculo create(final VeiculoDTO veiculoDTO){
        Veiculo veiculo = veiculoAdapter.toEntity(veiculoDTO);
        veiculo.setModelo(veiculoDTO.modelo());

        if (veiculoDTO.acessorios() != null && !veiculoDTO.acessorios().isEmpty()) {
            veiculoDTO.acessorios().forEach(acessorioDTO -> {
                Acessorio acessorio = new Acessorio();
                acessorio.setNome(acessorioDTO.nome());
                veiculo.addAcessorio(acessorio);
            });
        }
        return veiculoRepository.save(veiculo);
    }

    public Page<VeiculoDTO> findAll(final Pageable pageable){
        return veiculoRepository.findAll(pageable).map(veiculoAdapter::toDto);
    }

    public VeiculoDTO findById(final Long id){
        Veiculo veiculoFound = veiculoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Veículo not found!"));
        return veiculoAdapter.toDto(veiculoFound);
    }

    public void update(final Long id, final VeiculoDTO veiculoDTO){
        Veiculo veiculoFound = veiculoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Veículo not found!"));
        veiculoFound.setId(id);
        veiculoRepository.save(veiculoFound);
    }

    public void delete(final Long id){
        Veiculo veiculoFound = veiculoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Veículo not found!"));
        veiculoRepository.deleteById(id);
    }

    public Veiculo addAcessorio(final Long id, final VeiculoDTO veiculoDTO){
        Veiculo veiculoFound = veiculoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Veículo not found!"));
        //veiculoFound.setAcessorios(veiculoDTO.acessorios().stream().map(acessorioAdapter::toEntity).collect(Collectors.toList()));
        veiculoFound.setAcessorios(veiculoDTO.acessorios());
        return veiculoRepository.save(veiculoFound);
    }

    public Veiculo removeAcessorio(final Long idVeiculo, final Long idAcessorio){
        Veiculo veiculoFound = veiculoRepository.findById(idVeiculo).orElseThrow(() -> new EntityNotFoundException("Veículo not found!"));
        veiculoFound.getAcessorios().remove(idAcessorio);
        return veiculoRepository.save(veiculoFound);
    }
}

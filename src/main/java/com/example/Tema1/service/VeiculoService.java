package com.example.Tema1.service;

import com.example.Tema1.DTO.AcessorioDTO;
import com.example.Tema1.DTO.VeiculoDTO;
import com.example.Tema1.adapter.AcessorioAdapter;
import com.example.Tema1.adapter.VeiculoAdapter;
import com.example.Tema1.entity.Acessorio;
import com.example.Tema1.entity.Veiculo;
import com.example.Tema1.repository.AcessorioRepository;
import com.example.Tema1.repository.VeiculoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final AcessorioRepository  acessorioRepository;
    private final VeiculoAdapter veiculoAdapter;
    private final AcessorioAdapter acessorioAdapter;

    @Transactional
    public Veiculo create(final VeiculoDTO veiculoDTO) {
        Veiculo veiculo = veiculoAdapter.toEntity(veiculoDTO);

        if (veiculoDTO.acessorios() != null) {
            veiculoDTO.acessorios().forEach(acessorio -> {
                acessorio.setVeiculo(veiculo);
                acessorioRepository.save(acessorio);
            });
        }
        return veiculoRepository.save(veiculo);
    }

    @Transactional
    public Page<VeiculoDTO> findAll(final Pageable pageable){
        return veiculoRepository.findAll(pageable).map(veiculoAdapter::toDto);
    }

    @Transactional
    public VeiculoDTO findById(final Long id){
        Veiculo veiculoFound = veiculoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Veículo not found!"));
        return veiculoAdapter.toDto(veiculoFound);
    }

    @Transactional
    public void update(final Long id, final VeiculoDTO veiculoDTO){
        Veiculo veiculoFound = veiculoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Veículo not found!"));

        veiculoFound.setModelo(veiculoDTO.modelo());
        veiculoFound.setPlaca(veiculoDTO.placa());
        veiculoFound.setAnoFabricacao(veiculoDTO.anoFabricacao());

        veiculoFound.setAcessorios(veiculoDTO.acessorios());

        veiculoRepository.save(veiculoFound);
    }

    @Transactional
    public void delete(final Long id){
        Veiculo veiculoFound = veiculoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Veículo not found!"));
        veiculoRepository.deleteById(id);
    }

    @Transactional
    public Veiculo addAcessorio(final Long id, final AcessorioDTO acessorioDTO){
        Veiculo veiculoFound = veiculoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Veículo not found!"));
        Acessorio acessorio = acessorioAdapter.toEntity(acessorioDTO);
        veiculoFound.addAcessorio(acessorio);
        return veiculoRepository.save(veiculoFound);
    }

    @Transactional
    public Veiculo removeAcessorio(final Long idVeiculo, final Long idAcessorio){
        Veiculo veiculoFound = veiculoRepository.findById(idVeiculo).orElseThrow(() -> new EntityNotFoundException("Veículo not found!"));
        Acessorio acessorioFound = acessorioRepository.findById(idAcessorio).orElseThrow(() -> new EntityNotFoundException("Acessorio not found"));

        veiculoFound.removeAcessorio(acessorioFound);
        return veiculoRepository.save(veiculoFound);
    }
}

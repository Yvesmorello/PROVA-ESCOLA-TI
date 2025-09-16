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

@Service
@RequiredArgsConstructor
public class AcessorioService {

    private final AcessorioRepository acessorioRepository;
    private final VeiculoAdapter  veiculoAdapter;
    private final VeiculoRepository veiculoRepository;
    private final AcessorioAdapter adapter;

    public Acessorio create(final AcessorioDTO acessorioDTO){
        Acessorio acessorio = adapter.toEntity(acessorioDTO);

        if(acessorioDTO.veiculoId() != null){
            Veiculo veiculo =  veiculoRepository.findById(acessorioDTO.veiculoId()).orElseThrow(() -> new EntityNotFoundException("Veículo not found!"));
            acessorio.setVeiculo(veiculo);
        }
        return acessorioRepository.save(acessorio);
    }

    public Page<AcessorioDTO> findAll(final Pageable pageable){
        return acessorioRepository.findAll(pageable).map(adapter::toDto);
    }

    public AcessorioDTO findById(final Long id){
        Acessorio acessorioFound = acessorioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Veículo not found!"));
        return adapter.toDto(acessorioFound);
    }

    public void update(final Long id, final AcessorioDTO acessorioDTO){
        Acessorio acessorioFound = acessorioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Veículo not found!"));
        acessorioFound.setNome(acessorioDTO.nome());

        acessorioRepository.save(acessorioFound);
    }

    public void delete(final Long id){
        Acessorio acessorioFound = acessorioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Acessório not found!"));
        acessorioRepository.deleteById(id);
    }

}

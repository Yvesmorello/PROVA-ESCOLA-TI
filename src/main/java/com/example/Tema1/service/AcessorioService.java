package com.example.Tema1.service;

import com.example.Tema1.DTO.AcessorioDTO;
import com.example.Tema1.DTO.VeiculoDTO;
import com.example.Tema1.adapter.AcessorioAdapter;
import com.example.Tema1.entity.Acessorio;
import com.example.Tema1.entity.Veiculo;
import com.example.Tema1.repository.AcessorioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AcessorioService {

    private final AcessorioRepository acessorioRepository;
    private final AcessorioAdapter adapter;

    public Acessorio create(final AcessorioDTO acessorioDTO){
        Acessorio acessorio = adapter.toEntity(acessorioDTO);
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
        acessorioFound.setId(acessorioDTO.id());
        acessorioRepository.save(acessorioFound);
    }

    public void delete(final Long id){
        Acessorio acessorioFound = acessorioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Veículo not found!"));
        acessorioRepository.save(acessorioFound);
    }

}

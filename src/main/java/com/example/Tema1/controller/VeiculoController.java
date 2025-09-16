package com.example.Tema1.controller;

import com.example.Tema1.DTO.AcessorioDTO;
import com.example.Tema1.DTO.VeiculoDTO;
import com.example.Tema1.entity.Acessorio;
import com.example.Tema1.entity.Veiculo;
import com.example.Tema1.service.VeiculoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/veiculos")
@RequiredArgsConstructor
public class VeiculoController {

    private final VeiculoService service;

    @PostMapping
    public ResponseEntity<Veiculo> create(@RequestBody @Valid final VeiculoDTO veiculoDTO){
        Veiculo veiculo = service.create(veiculoDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(veiculo.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<Page<VeiculoDTO>> findAll(@PageableDefault final Pageable pageable){
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeiculoDTO> findById(@PathVariable final Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable final Long id, @RequestBody @Valid final VeiculoDTO veiculoDTO){
        service.update(id, veiculoDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/addAcessorio/{id}")
    public ResponseEntity<Veiculo> addAcessorio(@PathVariable final Long id, @RequestBody @Valid final AcessorioDTO acessorioDTO){
        Veiculo veiculo = service.addAcessorio(id, acessorioDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/removeAcessorio/{idVeiculo}/{idAcessorio}")
    public ResponseEntity<Veiculo> removeAcessorio(@PathVariable final Long idVeiculo, @PathVariable final Long idAcessorio){
        service.removeAcessorio(idVeiculo, idAcessorio);
        return ResponseEntity.noContent().build();
    }

}

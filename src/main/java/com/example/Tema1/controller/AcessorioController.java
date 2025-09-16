package com.example.Tema1.controller;

import com.example.Tema1.DTO.AcessorioDTO;
import com.example.Tema1.entity.Acessorio;
import com.example.Tema1.service.AcessorioService;
import com.example.Tema1.service.AcessorioService;
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
@RequestMapping("/acessorios")
@RequiredArgsConstructor
public class AcessorioController {

    private final AcessorioService service;

    @PostMapping
    public ResponseEntity<Acessorio> create(@RequestBody @Valid final AcessorioDTO acessorioDTO){
        service.create(acessorioDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/id")
                .buildAndExpand(acessorioDTO.id())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<Page<AcessorioDTO>> findAll(@PageableDefault final Pageable pageable){
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AcessorioDTO> findById(@PathVariable final Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable final Long id, @RequestBody @Valid final AcessorioDTO acessorioDTO){
        service.update(id, acessorioDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

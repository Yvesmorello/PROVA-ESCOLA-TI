package com.example.Tema1.DTO;

import com.example.Tema1.entity.Acessorio;
import com.example.Tema1.entity.Veiculo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

@Builder
public record VeiculoDTO(

        Long id,
        @NotBlank
        String modelo,
        @NotNull
        Integer anoFabricacao,
        @NotBlank
        String placa,
        //List<AcessorioDTO> acessorios
        List<Acessorio> acessorios
) {
}


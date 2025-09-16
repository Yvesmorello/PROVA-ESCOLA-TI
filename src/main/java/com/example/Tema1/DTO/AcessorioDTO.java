package com.example.Tema1.DTO;

import com.example.Tema1.entity.Veiculo;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.util.List;

@Builder
public record AcessorioDTO(

        Long id,
        @NotBlank
        String nome,
        Veiculo veiculo
) {
}

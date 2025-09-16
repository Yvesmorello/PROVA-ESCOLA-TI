package com.example.Tema1.adapter;


import com.example.Tema1.DTO.AcessorioDTO;
import com.example.Tema1.entity.Acessorio;
import com.example.Tema1.entity.Veiculo;
import com.example.Tema1.repository.VeiculoRepository;
import org.springframework.stereotype.Component;


import java.util.stream.Collectors;

@Component
public class AcessorioAdapter implements Adapter<Acessorio, AcessorioDTO> {

    private VeiculoAdapter veiculoAdapter;
    private VeiculoRepository  veiculoRepository;

    @Override
    public Acessorio toEntity(AcessorioDTO dto) {

        Veiculo veiculo = null;
        if (dto.veiculo() != null) {
            veiculo = veiculoRepository.findById(dto.veiculo().getId()).orElse(null);
        }
        Acessorio acessorio = new Acessorio();

        acessorio.setId(dto.id());
        acessorio.setNome(dto.nome());
        acessorio.setVeiculo(dto.veiculo());

        return acessorio;
    }

    @Override
    public AcessorioDTO toDto(Acessorio entity) {
        return AcessorioDTO.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .veiculo(entity.getVeiculo())
                .build();
    }
}

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
        Acessorio acessorio = new Acessorio();
        acessorio.setId(dto.id());
        acessorio.setNome(dto.nome());
        return acessorio;
    }

    @Override
    public AcessorioDTO toDto(Acessorio entity) {
        return AcessorioDTO.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .veiculoId(entity.getVeiculo() != null ? entity.getVeiculo().getId() : null)
                .build();
    }
}

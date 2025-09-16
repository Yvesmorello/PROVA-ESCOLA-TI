package com.example.Tema1.adapter;

import com.example.Tema1.DTO.AcessorioDTO;
import com.example.Tema1.DTO.VeiculoDTO;
import com.example.Tema1.entity.Veiculo;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class VeiculoAdapter implements Adapter<Veiculo, VeiculoDTO> {

    private AcessorioAdapter acessorioAdapter;


    @Override
    public Veiculo toEntity(VeiculoDTO dto) {
        Veiculo veiculo = new Veiculo();

        veiculo.setId(dto.id());
        veiculo.setModelo(dto.modelo());
        veiculo.setPlaca(dto.placa());
        veiculo.setAnoFabricacao(dto.anoFabricacao());
        veiculo.setAcessorios(dto.acessorios());

        return veiculo;
}

    @Override
    public VeiculoDTO toDto(Veiculo entity) {
        return VeiculoDTO.builder()
                .id(entity.getId())
                .modelo(entity.getModelo())
                .placa(entity.getPlaca())
                .anoFabricacao(entity.getAnoFabricacao())
                .acessorios(entity.getAcessorios())
                .build();

    }
}

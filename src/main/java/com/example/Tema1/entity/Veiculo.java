package com.example.Tema1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Table
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotBlank
    private String modelo;

    @Column
    @NotNull
    private Integer anoFabricacao;

    @Column(unique = true)
    @NotBlank
    private String placa;

    @OneToMany(mappedBy = "veiculo",  fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Acessorio> acessorios;

    public void addAcessorio(Acessorio acessorio) {
        this.acessorios.add(acessorio);
        acessorio.setVeiculo(this);
    }

    public void removeAcessorio(Acessorio acessorio) {
        this.acessorios.remove(acessorio);
    }
}

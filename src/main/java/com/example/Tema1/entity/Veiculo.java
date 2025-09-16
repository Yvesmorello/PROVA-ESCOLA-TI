package com.example.Tema1.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @Column
    @OneToMany(mappedBy = "veiculo",  fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Acessorio> acessorios;

    public void addAcessorio(Acessorio acessorio) {
        this.acessorios.add(acessorio);
        acessorio.setVeiculo(this);
    }

    public void removeAcessorio(Acessorio acessorio) {
        this.acessorios.remove(acessorio);
        acessorio.setVeiculo(null);
    }
}

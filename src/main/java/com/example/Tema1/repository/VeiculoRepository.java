package com.example.Tema1.repository;

import com.example.Tema1.entity.Acessorio;
import com.example.Tema1.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
}

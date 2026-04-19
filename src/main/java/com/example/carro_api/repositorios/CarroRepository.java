package com.example.carro_api.repositorios;

import com.example.carro_api.entidade.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroRepository extends JpaRepository <Carro, Long> {
}

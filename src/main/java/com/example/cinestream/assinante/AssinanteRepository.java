package com.example.cinestream.assinante;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AssinanteRepository extends JpaRepository<Assinante, Integer> {

    boolean existsByCpf(String cpf);
}

package com.example.cinestream.assinante;

import java.io.Serializable;

public record AssinanteResponse(
        Integer id,
        String nome,
        String cpf,
        String email,
        String telefone,
        Boolean contaAtiva) implements Serializable {

    public static AssinanteResponse fromEntity(Assinante assinante) {
        return new AssinanteResponse(
                assinante.getId(),
                assinante.getNome(),
                assinante.getCpf(),
                assinante.getEmail(),
                assinante.getTelefone(),
                assinante.getContaAtiva());
    }
}

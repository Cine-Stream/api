package com.example.cinestream.obra;

import java.io.Serializable;

public record ObraResponse(
        Integer id,
        String titulo,
        String sinopse,
        Short anoLancamento,
        Short idadeMinimaRecomendada) implements Serializable {

    public static ObraResponse fromEntity(Obra obra) {
        return new ObraResponse(
                obra.getId(),
                obra.getTitulo(),
                obra.getSinopse(),
                obra.getAnoLancamento(),
                obra.getIdadeMinimaRecomendada());
    }

}

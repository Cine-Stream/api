package com.example.cinestream.qualidaderesolucao;

/**
 * Valores aceitos pela constraint chk_qualidade da tabela qualidade_resolucao.
 */
public enum QualidadeResolucaoDescricao {

    SD("SD"),
    FULL_HD("FULL-HD"),
    QUALIDADE_4K("4K");

    private final String valorBanco;

    QualidadeResolucaoDescricao(String valorBanco) {
        this.valorBanco = valorBanco;
    }

    @com.fasterxml.jackson.annotation.JsonValue
    public String getValorBanco() {
        return valorBanco;
    }

    public static QualidadeResolucaoDescricao fromValorBanco(String valor) {
        if (valor == null) {
            return null;
        }
        for (QualidadeResolucaoDescricao qualidade : values()) {
            if (qualidade.valorBanco.equals(valor)) {
                return qualidade;
            }
        }
        throw new IllegalArgumentException("Qualidade de resolucao invalida: " + valor);
    }
}

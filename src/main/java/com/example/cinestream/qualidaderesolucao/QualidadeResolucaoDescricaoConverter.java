package com.example.cinestream.qualidaderesolucao;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * Converte o enum para os valores gravados no banco ('SD', 'FULL-HD', '4K').
 */
@Converter
public class QualidadeResolucaoDescricaoConverter
        implements AttributeConverter<QualidadeResolucaoDescricao, String> {

    @Override
    public String convertToDatabaseColumn(QualidadeResolucaoDescricao descricao) {
        return descricao == null ? null : descricao.getValorBanco();
    }

    @Override
    public QualidadeResolucaoDescricao convertToEntityAttribute(String valor) {
        return QualidadeResolucaoDescricao.fromValorBanco(valor);
    }
}

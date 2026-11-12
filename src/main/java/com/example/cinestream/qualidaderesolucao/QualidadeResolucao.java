package com.example.cinestream.qualidaderesolucao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "qualidade_resolucao")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class QualidadeResolucao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_qualidade")
    private Integer id;

    @Convert(converter = QualidadeResolucaoDescricaoConverter.class)
    @Column(nullable = false, unique = true, length = 20)
    private QualidadeResolucaoDescricao descricao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public QualidadeResolucaoDescricao getDescricao() {
        return descricao;
    }

    public void setDescricao(QualidadeResolucaoDescricao descricao) {
        this.descricao = descricao;
    }
}

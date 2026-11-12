package com.example.cinestream.plano;

import java.util.ArrayList;
import java.util.List;

import com.example.cinestream.assinatura.Assinatura;
import com.example.cinestream.qualidaderesolucao.QualidadeResolucao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "plano")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Plano {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plano")
    private Integer id;

    @Column(name = "nome_comercial", nullable = false, length = 100)
    private String nomeComercial;

    @Column(name = "max_telas", nullable = false)
    private Short maxTelas;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_qualidade", nullable = false)
    private QualidadeResolucao qualidadeResolucao;

    @JsonIgnore
    @OneToMany(mappedBy = "plano")
    private List<Assinatura> assinaturas = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeComercial() {
        return nomeComercial;
    }

    public void setNomeComercial(String nomeComercial) {
        this.nomeComercial = nomeComercial;
    }

    public Short getMaxTelas() {
        return maxTelas;
    }

    public void setMaxTelas(Short maxTelas) {
        this.maxTelas = maxTelas;
    }

    public QualidadeResolucao getQualidadeResolucao() {
        return qualidadeResolucao;
    }

    public void setQualidadeResolucao(QualidadeResolucao qualidadeResolucao) {
        this.qualidadeResolucao = qualidadeResolucao;
    }

    public List<Assinatura> getAssinaturas() {
        return assinaturas;
    }

    public void setAssinaturas(List<Assinatura> assinaturas) {
        this.assinaturas = assinaturas;
    }
}

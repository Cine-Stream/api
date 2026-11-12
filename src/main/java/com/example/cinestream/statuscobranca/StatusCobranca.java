package com.example.cinestream.statuscobranca;

import java.util.ArrayList;
import java.util.List;

import com.example.cinestream.fatura.Fatura;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "status_cobranca")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class StatusCobranca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_status")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "nome", nullable = false, unique = true, length = 20)
    private StatusCobrancaDescricao nome;

    @JsonIgnore
    @OneToMany(mappedBy = "statusCobranca")
    private List<Fatura> faturas = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public StatusCobrancaDescricao getNome() {
        return nome;
    }

    public void setNome(StatusCobrancaDescricao nome) {
        this.nome = nome;
    }

    public List<Fatura> getFaturas() {
        return faturas;
    }

    public void setFaturas(List<Fatura> faturas) {
        this.faturas = faturas;
    }
}

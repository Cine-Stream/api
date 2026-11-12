package com.example.cinestream.classificacaomaturidade;

import java.util.ArrayList;
import java.util.List;

import com.example.cinestream.perfil.Perfil;
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
@Table(name = "classificacao_maturidade")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class ClassificacaoMaturidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_classificacao")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ClassificacaoDescricao descricao;

    @JsonIgnore
    @OneToMany(mappedBy = "classificacaoMaturidade")
    private List<Perfil> perfis = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ClassificacaoDescricao getDescricao() {
        return descricao;
    }

    public void setDescricao(ClassificacaoDescricao descricao) {
        this.descricao = descricao;
    }

    public List<Perfil> getPerfis() {
        return perfis;
    }

    public void setPerfis(List<Perfil> perfis) {
        this.perfis = perfis;
    }
}

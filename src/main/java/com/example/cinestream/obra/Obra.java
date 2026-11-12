package com.example.cinestream.obra;

import java.util.ArrayList;
import java.util.List;

import com.example.cinestream.episodio.Episodio;
import com.example.cinestream.obragenero.ObraGenero;
import com.example.cinestream.obraprofissional.ObraProfissional;
import com.example.cinestream.visualizacao.Visualizacao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "obra")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Obra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_obra")
    private Integer id;

    @Column(nullable = false, length = 200)
    private String titulo;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String sinopse;

    @Column(name = "ano_lancamento", nullable = false)
    private Short anoLancamento;

    @Column(name = "idade_minima_recomendada", nullable = false)
    private Short idadeMinimaRecomendada;

    @JsonIgnore
    @OneToMany(mappedBy = "obra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ObraGenero> generos = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "obra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ObraProfissional> profissionais = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "obra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Episodio> episodios = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "obra")
    private List<Visualizacao> visualizacoes = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public Short getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(Short anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public Short getIdadeMinimaRecomendada() {
        return idadeMinimaRecomendada;
    }

    public void setIdadeMinimaRecomendada(Short idadeMinimaRecomendada) {
        this.idadeMinimaRecomendada = idadeMinimaRecomendada;
    }

    public List<ObraGenero> getGeneros() {
        return generos;
    }

    public void setGeneros(List<ObraGenero> generos) {
        this.generos = generos;
    }

    public List<ObraProfissional> getProfissionais() {
        return profissionais;
    }

    public void setProfissionais(List<ObraProfissional> profissionais) {
        this.profissionais = profissionais;
    }

    public List<Episodio> getEpisodios() {
        return episodios;
    }

    public void setEpisodios(List<Episodio> episodios) {
        this.episodios = episodios;
    }

    public List<Visualizacao> getVisualizacoes() {
        return visualizacoes;
    }

    public void setVisualizacoes(List<Visualizacao> visualizacoes) {
        this.visualizacoes = visualizacoes;
    }
}

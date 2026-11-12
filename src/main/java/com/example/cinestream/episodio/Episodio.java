package com.example.cinestream.episodio;

import com.example.cinestream.obra.Obra;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "episodio")
public class Episodio {

    @EmbeddedId
    private EpisodioId id;

    @MapsId("obraId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_obra", nullable = false)
    private Obra obra;

    @Column(nullable = false, length = 200)
    private String titulo;

    @Column(nullable = false)
    private Short duracao;

    public EpisodioId getId() {
        return id;
    }

    public void setId(EpisodioId id) {
        this.id = id;
    }

    public Obra getObra() {
        return obra;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Short getDuracao() {
        return duracao;
    }

    public void setDuracao(Short duracao) {
        this.duracao = duracao;
    }
}

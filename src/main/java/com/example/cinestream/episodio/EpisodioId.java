package com.example.cinestream.episodio;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class EpisodioId implements Serializable {

    @Column(name = "id_obra")
    private Integer obraId;

    @Column(name = "numero_temporada")
    private Short numeroTemporada;

    @Column(name = "numero_capitulo")
    private Short numeroCapitulo;

    public EpisodioId() {
    }

    public EpisodioId(Integer obraId, Short numeroTemporada, Short numeroCapitulo) {
        this.obraId = obraId;
        this.numeroTemporada = numeroTemporada;
        this.numeroCapitulo = numeroCapitulo;
    }

    public Integer getObraId() {
        return obraId;
    }

    public void setObraId(Integer obraId) {
        this.obraId = obraId;
    }

    public Short getNumeroTemporada() {
        return numeroTemporada;
    }

    public void setNumeroTemporada(Short numeroTemporada) {
        this.numeroTemporada = numeroTemporada;
    }

    public Short getNumeroCapitulo() {
        return numeroCapitulo;
    }

    public void setNumeroCapitulo(Short numeroCapitulo) {
        this.numeroCapitulo = numeroCapitulo;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof EpisodioId that)) {
            return false;
        }
        return Objects.equals(obraId, that.obraId)
                && Objects.equals(numeroTemporada, that.numeroTemporada)
                && Objects.equals(numeroCapitulo, that.numeroCapitulo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(obraId, numeroTemporada, numeroCapitulo);
    }
}

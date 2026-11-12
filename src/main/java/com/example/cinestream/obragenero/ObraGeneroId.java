package com.example.cinestream.obragenero;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ObraGeneroId implements Serializable {

    @Column(name = "id_obra")
    private Integer obraId;

    @Column(name = "id_genero")
    private Integer generoId;

    public ObraGeneroId() {
    }

    public ObraGeneroId(Integer obraId, Integer generoId) {
        this.obraId = obraId;
        this.generoId = generoId;
    }

    public Integer getObraId() {
        return obraId;
    }

    public void setObraId(Integer obraId) {
        this.obraId = obraId;
    }

    public Integer getGeneroId() {
        return generoId;
    }

    public void setGeneroId(Integer generoId) {
        this.generoId = generoId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof ObraGeneroId that)) {
            return false;
        }
        return Objects.equals(obraId, that.obraId) && Objects.equals(generoId, that.generoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(obraId, generoId);
    }
}

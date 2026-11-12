package com.example.cinestream.perfil;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class PerfilId implements Serializable {

    @Column(name = "id_assinante")
    private Integer assinanteId;

    @Column(name = "numero_sequencial")
    private Integer numeroSequencial;

    public PerfilId() {
    }

    public PerfilId(Integer assinanteId, Integer numeroSequencial) {
        this.assinanteId = assinanteId;
        this.numeroSequencial = numeroSequencial;
    }

    public Integer getAssinanteId() {
        return assinanteId;
    }

    public void setAssinanteId(Integer assinanteId) {
        this.assinanteId = assinanteId;
    }

    public Integer getNumeroSequencial() {
        return numeroSequencial;
    }

    public void setNumeroSequencial(Integer numeroSequencial) {
        this.numeroSequencial = numeroSequencial;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof PerfilId perfilId)) {
            return false;
        }
        return Objects.equals(assinanteId, perfilId.assinanteId)
                && Objects.equals(numeroSequencial, perfilId.numeroSequencial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(assinanteId, numeroSequencial);
    }
}

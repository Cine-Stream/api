package com.example.cinestream.assinatura;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class AssinaturaId implements Serializable {

    @Column(name = "id_assinante")
    private Integer assinanteId;

    @Column(name = "id_plano")
    private Integer planoId;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    public AssinaturaId() {
    }

    public AssinaturaId(Integer assinanteId, Integer planoId, LocalDate dataInicio) {
        this.assinanteId = assinanteId;
        this.planoId = planoId;
        this.dataInicio = dataInicio;
    }

    public Integer getAssinanteId() {
        return assinanteId;
    }

    public void setAssinanteId(Integer assinanteId) {
        this.assinanteId = assinanteId;
    }

    public Integer getPlanoId() {
        return planoId;
    }

    public void setPlanoId(Integer planoId) {
        this.planoId = planoId;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof AssinaturaId that)) {
            return false;
        }
        return Objects.equals(assinanteId, that.assinanteId)
                && Objects.equals(planoId, that.planoId)
                && Objects.equals(dataInicio, that.dataInicio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(assinanteId, planoId, dataInicio);
    }
}

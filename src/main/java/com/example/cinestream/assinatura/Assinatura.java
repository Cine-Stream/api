package com.example.cinestream.assinatura;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.cinestream.assinante.Assinante;
import com.example.cinestream.plano.Plano;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

/**
 * Historico de assinaturas. A tabela historico_assinatura tem chave primaria
 * composta (id_assinante, id_plano, data_inicio).
 */
@Entity
@Table(name = "historico_assinatura")
public class Assinatura {

    @EmbeddedId
    private AssinaturaId id;

    @MapsId("assinanteId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_assinante", nullable = false)
    private Assinante assinante;

    @MapsId("planoId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_plano", nullable = false)
    private Plano plano;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    @Column(name = "valor_cobrado", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorCobrado;

    public AssinaturaId getId() {
        return id;
    }

    public void setId(AssinaturaId id) {
        this.id = id;
    }

    public Assinante getAssinante() {
        return assinante;
    }

    public void setAssinante(Assinante assinante) {
        this.assinante = assinante;
    }

    public Plano getPlano() {
        return plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public BigDecimal getValorCobrado() {
        return valorCobrado;
    }

    public void setValorCobrado(BigDecimal valorCobrado) {
        this.valorCobrado = valorCobrado;
    }
}

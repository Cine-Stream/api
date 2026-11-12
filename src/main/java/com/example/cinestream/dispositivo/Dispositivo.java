package com.example.cinestream.dispositivo;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.example.cinestream.assinante.Assinante;
import com.example.cinestream.visualizacao.Visualizacao;
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
@Table(name = "dispositivo")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Dispositivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dispositivo")
    private Integer id;

    /** No banco o MAC e CHAR(17), no formato AA:BB:CC:DD:EE:FF. */
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "endereco_mac", nullable = false, unique = true, length = 17)
    private String enderecoMac;

    @Column(name = "sistema_operacional", nullable = false, length = 50)
    private String sistemaOperacional;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_assinante", nullable = false)
    private Assinante assinante;

    @JsonIgnore
    @OneToMany(mappedBy = "dispositivo")
    private List<Visualizacao> visualizacoes = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEnderecoMac() {
        // CHAR(17) no PostgreSQL volta preenchido com espacos a direita.
        return enderecoMac == null ? null : enderecoMac.trim();
    }

    public void setEnderecoMac(String enderecoMac) {
        this.enderecoMac = enderecoMac == null ? null : enderecoMac.trim();
    }

    public String getSistemaOperacional() {
        return sistemaOperacional;
    }

    public void setSistemaOperacional(String sistemaOperacional) {
        this.sistemaOperacional = sistemaOperacional;
    }

    public Assinante getAssinante() {
        return assinante;
    }

    public void setAssinante(Assinante assinante) {
        this.assinante = assinante;
    }

    public List<Visualizacao> getVisualizacoes() {
        return visualizacoes;
    }

    public void setVisualizacoes(List<Visualizacao> visualizacoes) {
        this.visualizacoes = visualizacoes;
    }
}

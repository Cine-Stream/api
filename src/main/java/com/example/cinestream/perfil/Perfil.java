package com.example.cinestream.perfil;

import java.util.ArrayList;
import java.util.List;

import com.example.cinestream.assinante.Assinante;
import com.example.cinestream.classificacaomaturidade.ClassificacaoMaturidade;
import com.example.cinestream.visualizacao.Visualizacao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
        name = "perfil",
        uniqueConstraints = @UniqueConstraint(
                name = "uq_perfil_nome",
                columnNames = {"id_assinante", "nome_exibicao"}))
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Perfil {

    @EmbeddedId
    private PerfilId id;

    @MapsId("assinanteId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_assinante", nullable = false)
    private Assinante assinante;

    @Column(name = "nome_exibicao", nullable = false, length = 100)
    private String nomeExibicao;

    @Column(name = "avatar_url", length = 255)
    private String avatarUrl;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_classificacao", nullable = false)
    private ClassificacaoMaturidade classificacaoMaturidade;

    @JsonIgnore
    @OneToMany(mappedBy = "perfil", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Visualizacao> visualizacoes = new ArrayList<>();

    public PerfilId getId() {
        return id;
    }

    public void setId(PerfilId id) {
        this.id = id;
    }

    public Assinante getAssinante() {
        return assinante;
    }

    public void setAssinante(Assinante assinante) {
        this.assinante = assinante;
    }

    public String getNomeExibicao() {
        return nomeExibicao;
    }

    public void setNomeExibicao(String nomeExibicao) {
        this.nomeExibicao = nomeExibicao;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public ClassificacaoMaturidade getClassificacaoMaturidade() {
        return classificacaoMaturidade;
    }

    public void setClassificacaoMaturidade(ClassificacaoMaturidade classificacaoMaturidade) {
        this.classificacaoMaturidade = classificacaoMaturidade;
    }

    public List<Visualizacao> getVisualizacoes() {
        return visualizacoes;
    }

    public void setVisualizacoes(List<Visualizacao> visualizacoes) {
        this.visualizacoes = visualizacoes;
    }
}

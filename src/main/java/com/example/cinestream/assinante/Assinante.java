package com.example.cinestream.assinante;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.example.cinestream.assinatura.Assinatura;
import com.example.cinestream.dispositivo.Dispositivo;
import com.example.cinestream.fatura.Fatura;
import com.example.cinestream.perfil.Perfil;
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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "assinante")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Assinante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_assinante")
    private Integer id;

    @NotBlank
    @Size(max = 150)
    @Column(name = "nome_completo", nullable = false, length = 150)
    private String nome;

    /** No banco o CPF e CHAR(14), no formato 000.000.000-00. */
    @NotBlank
    @Size(min = 14, max = 14)
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(nullable = false, unique = true, length = 14)
    private String cpf;

    @NotBlank
    @Email
    @Size(max = 150)
    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @NotBlank
    @Size(max = 20)
    @Column(nullable = false, length = 20)
    private String telefone;

    @NotNull
    @Column(name = "conta_ativa", nullable = false)
    private Boolean contaAtiva = true;

    @JsonIgnore
    @OneToMany(mappedBy = "assinante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Perfil> perfis = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "assinante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dispositivo> dispositivos = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "assinante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Assinatura> assinaturas = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "assinante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Fatura> faturas = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        // CHAR(14) no PostgreSQL volta preenchido com espacos a direita.
        return cpf == null ? null : cpf.trim();
    }

    public void setCpf(String cpf) {
        this.cpf = cpf == null ? null : cpf.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Boolean getContaAtiva() {
        return contaAtiva;
    }

    public void setContaAtiva(Boolean contaAtiva) {
        this.contaAtiva = contaAtiva;
    }

    public List<Perfil> getPerfis() {
        return perfis;
    }

    public void setPerfis(List<Perfil> perfis) {
        this.perfis = perfis;
    }

    public List<Dispositivo> getDispositivos() {
        return dispositivos;
    }

    public void setDispositivos(List<Dispositivo> dispositivos) {
        this.dispositivos = dispositivos;
    }

    public List<Assinatura> getAssinaturas() {
        return assinaturas;
    }

    public void setAssinaturas(List<Assinatura> assinaturas) {
        this.assinaturas = assinaturas;
    }

    public List<Fatura> getFaturas() {
        return faturas;
    }

    public void setFaturas(List<Fatura> faturas) {
        this.faturas = faturas;
    }
}

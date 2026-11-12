package com.example.cinestream.qualidaderesolucao;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/qualidades-resolucao")
public class QualidadeResolucaoController {

    private final QualidadeResolucaoService service;

    public QualidadeResolucaoController(QualidadeResolucaoService service) {
        this.service = service;
    }

    @GetMapping
    public List<QualidadeResolucao> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public QualidadeResolucao buscar(@PathVariable Integer id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public QualidadeResolucao criar(@Valid @RequestBody QualidadeResolucao qualidade) {
        return service.criar(qualidade);
    }

    @PutMapping("/{id}")
    public QualidadeResolucao atualizar(@PathVariable Integer id, @Valid @RequestBody QualidadeResolucao qualidade) {
        qualidade.setId(id);
        return service.atualizar(id, qualidade);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Integer id) {
        service.excluir(id);
    }
}

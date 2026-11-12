package com.example.cinestream.visualizacao;

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
@RequestMapping("/visualizacoes")
public class VisualizacaoController {

    private final VisualizacaoService service;

    public VisualizacaoController(VisualizacaoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Visualizacao> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Visualizacao buscar(@PathVariable Integer id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Visualizacao criar(@Valid @RequestBody Visualizacao visualizacao) {
        return service.criar(visualizacao);
    }

    @PutMapping("/{id}")
    public Visualizacao atualizar(@PathVariable Integer id, @Valid @RequestBody Visualizacao visualizacao) {
        visualizacao.setId(id);
        return service.atualizar(id, visualizacao);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Integer id) {
        service.excluir(id);
    }
}

package com.example.cinestream.statuscobranca;

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
@RequestMapping("/status-cobranca")
public class StatusCobrancaController {

    private final StatusCobrancaService service;

    public StatusCobrancaController(StatusCobrancaService service) {
        this.service = service;
    }

    @GetMapping
    public List<StatusCobranca> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public StatusCobranca buscar(@PathVariable Integer id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StatusCobranca criar(@Valid @RequestBody StatusCobranca statusCobranca) {
        return service.criar(statusCobranca);
    }

    @PutMapping("/{id}")
    public StatusCobranca atualizar(@PathVariable Integer id, @Valid @RequestBody StatusCobranca statusCobranca) {
        statusCobranca.setId(id);
        return service.atualizar(id, statusCobranca);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Integer id) {
        service.excluir(id);
    }
}

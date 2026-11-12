package com.example.cinestream.assinante;

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
@RequestMapping("/assinantes")
public class AssinanteController {

    private final AssinanteService service;

    public AssinanteController(AssinanteService service) {
        this.service = service;
    }

    @GetMapping
    public List<AssinanteResponse> listar() {
        return service.listarComCache();
    }

    @GetMapping("/{id}")
    public Assinante buscar(@PathVariable Integer id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Assinante criar(@Valid @RequestBody Assinante assinante) {
        return service.criar(assinante);
    }

    @PutMapping("/{id}")
    public Assinante atualizar(@PathVariable Integer id, @Valid @RequestBody Assinante assinante) {
        assinante.setId(id);
        return service.atualizar(id, assinante);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Integer id) {
        service.excluir(id);
    }
}

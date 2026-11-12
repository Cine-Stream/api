package com.example.cinestream.obragenero;

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
@RequestMapping("/obras-generos")
public class ObraGeneroController {

    private final ObraGeneroService service;

    public ObraGeneroController(ObraGeneroService service) {
        this.service = service;
    }

    @GetMapping
    public List<ObraGenero> listar() {
        return service.listar();
    }

    @GetMapping("/{obraId}/{generoId}")
    public ObraGenero buscar(@PathVariable Integer obraId, @PathVariable Integer generoId) {
        return service.buscarPorId(new ObraGeneroId(obraId, generoId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ObraGenero criar(@Valid @RequestBody ObraGenero obraGenero) {
        return service.criar(obraGenero);
    }

    @PutMapping("/{obraId}/{generoId}")
    public ObraGenero atualizar(@PathVariable Integer obraId, @PathVariable Integer generoId,
            @Valid @RequestBody ObraGenero obraGenero) {
        ObraGeneroId id = new ObraGeneroId(obraId, generoId);
        obraGenero.setId(id);
        return service.atualizar(id, obraGenero);
    }

    @DeleteMapping("/{obraId}/{generoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Integer obraId, @PathVariable Integer generoId) {
        service.excluir(new ObraGeneroId(obraId, generoId));
    }
}

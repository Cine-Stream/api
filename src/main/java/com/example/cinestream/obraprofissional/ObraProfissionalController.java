package com.example.cinestream.obraprofissional;

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
@RequestMapping("/obras-profissionais")
public class ObraProfissionalController {

    private final ObraProfissionalService service;

    public ObraProfissionalController(ObraProfissionalService service) {
        this.service = service;
    }

    @GetMapping
    public List<ObraProfissional> listar() {
        return service.listar();
    }

    @GetMapping("/{profissionalId}/{obraId}/{funcao}")
    public ObraProfissional buscar(@PathVariable Integer profissionalId, @PathVariable Integer obraId,
            @PathVariable FuncaoProfissional funcao) {
        return service.buscarPorId(new ObraProfissionalId(profissionalId, obraId, funcao));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ObraProfissional criar(@Valid @RequestBody ObraProfissional obraProfissional) {
        return service.criar(obraProfissional);
    }

    @PutMapping("/{profissionalId}/{obraId}/{funcao}")
    public ObraProfissional atualizar(@PathVariable Integer profissionalId, @PathVariable Integer obraId,
            @PathVariable FuncaoProfissional funcao, @Valid @RequestBody ObraProfissional obraProfissional) {
        ObraProfissionalId id = new ObraProfissionalId(profissionalId, obraId, funcao);
        obraProfissional.setId(id);
        return service.atualizar(id, obraProfissional);
    }

    @DeleteMapping("/{profissionalId}/{obraId}/{funcao}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Integer profissionalId, @PathVariable Integer obraId,
            @PathVariable FuncaoProfissional funcao) {
        service.excluir(new ObraProfissionalId(profissionalId, obraId, funcao));
    }
}

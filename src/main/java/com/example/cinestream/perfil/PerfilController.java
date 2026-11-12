package com.example.cinestream.perfil;

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
@RequestMapping("/perfis")
public class PerfilController {

    private final PerfilService service;

    public PerfilController(PerfilService service) {
        this.service = service;
    }

    @GetMapping
    public List<Perfil> listar() {
        return service.listar();
    }

    @GetMapping("/{assinanteId}/{numeroSequencial}")
    public Perfil buscar(@PathVariable Integer assinanteId, @PathVariable Integer numeroSequencial) {
        return service.buscarPorId(new PerfilId(assinanteId, numeroSequencial));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Perfil criar(@Valid @RequestBody Perfil perfil) {
        return service.criar(perfil);
    }

    @PutMapping("/{assinanteId}/{numeroSequencial}")
    public Perfil atualizar(@PathVariable Integer assinanteId, @PathVariable Integer numeroSequencial,
            @Valid @RequestBody Perfil perfil) {
        PerfilId id = new PerfilId(assinanteId, numeroSequencial);
        perfil.setId(id);
        return service.atualizar(id, perfil);
    }

    @DeleteMapping("/{assinanteId}/{numeroSequencial}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Integer assinanteId, @PathVariable Integer numeroSequencial) {
        service.excluir(new PerfilId(assinanteId, numeroSequencial));
    }
}

package com.example.cinestream.obra;

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
@RequestMapping("/obras")
public class ObraController {

    private final ObraService service;

    public ObraController(ObraService service) {
        this.service = service;
    }

    @GetMapping
    public List<ObraResponse> listar() {
        return service.listarComCache();
    }

    @GetMapping("/{id}")
    public Obra buscar(@PathVariable Integer id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Obra criar(@Valid @RequestBody Obra obra) {
        return service.criar(obra);
    }

    @PutMapping("/{id}")
    public Obra atualizar(@PathVariable Integer id, @Valid @RequestBody Obra obra) {
        obra.setId(id);
        return service.atualizar(id, obra);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Integer id) {
        service.excluir(id);
    }
}

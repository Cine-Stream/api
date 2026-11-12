package com.example.cinestream.dispositivo;

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
@RequestMapping("/dispositivos")
public class DispositivoController {

    private final DispositivoService service;

    public DispositivoController(DispositivoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Dispositivo> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Dispositivo buscar(@PathVariable Integer id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dispositivo criar(@Valid @RequestBody Dispositivo dispositivo) {
        return service.criar(dispositivo);
    }

    @PutMapping("/{id}")
    public Dispositivo atualizar(@PathVariable Integer id, @Valid @RequestBody Dispositivo dispositivo) {
        dispositivo.setId(id);
        return service.atualizar(id, dispositivo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Integer id) {
        service.excluir(id);
    }
}

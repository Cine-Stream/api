package com.example.cinestream.episodio;

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
@RequestMapping("/episodios")
public class EpisodioController {

    private final EpisodioService service;

    public EpisodioController(EpisodioService service) {
        this.service = service;
    }

    @GetMapping
    public List<Episodio> listar() {
        return service.listar();
    }

    @GetMapping("/{obraId}/{numeroTemporada}/{numeroCapitulo}")
    public Episodio buscar(@PathVariable Integer obraId, @PathVariable Short numeroTemporada,
            @PathVariable Short numeroCapitulo) {
        return service.buscarPorId(new EpisodioId(obraId, numeroTemporada, numeroCapitulo));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Episodio criar(@Valid @RequestBody Episodio episodio) {
        return service.criar(episodio);
    }

    @PutMapping("/{obraId}/{numeroTemporada}/{numeroCapitulo}")
    public Episodio atualizar(@PathVariable Integer obraId, @PathVariable Short numeroTemporada,
            @PathVariable Short numeroCapitulo, @Valid @RequestBody Episodio episodio) {
        EpisodioId id = new EpisodioId(obraId, numeroTemporada, numeroCapitulo);
        episodio.setId(id);
        return service.atualizar(id, episodio);
    }

    @DeleteMapping("/{obraId}/{numeroTemporada}/{numeroCapitulo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Integer obraId, @PathVariable Short numeroTemporada,
            @PathVariable Short numeroCapitulo) {
        service.excluir(new EpisodioId(obraId, numeroTemporada, numeroCapitulo));
    }
}

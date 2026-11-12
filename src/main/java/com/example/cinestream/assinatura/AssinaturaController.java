package com.example.cinestream.assinatura;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
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
@RequestMapping("/assinaturas")
public class AssinaturaController {

    private final AssinaturaService service;

    public AssinaturaController(AssinaturaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Assinatura> listar() {
        return service.listar();
    }

    @GetMapping("/{idAssinante}/{idPlano}/{dataInicio}")
    public Assinatura buscar(
            @PathVariable Integer idAssinante,
            @PathVariable Integer idPlano,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio) {
        return service.buscarPorId(new AssinaturaId(idAssinante, idPlano, dataInicio));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Assinatura criar(@Valid @RequestBody AssinaturaRequest request) {
        return service.criar(request);
    }

    @PutMapping("/{idAssinante}/{idPlano}/{dataInicio}")
    public Assinatura atualizar(
            @PathVariable Integer idAssinante,
            @PathVariable Integer idPlano,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @Valid @RequestBody AssinaturaUpdateRequest request) {
        return service.atualizar(new AssinaturaId(idAssinante, idPlano, dataInicio), request);
    }

    @DeleteMapping("/{idAssinante}/{idPlano}/{dataInicio}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(
            @PathVariable Integer idAssinante,
            @PathVariable Integer idPlano,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio) {
        service.excluir(new AssinaturaId(idAssinante, idPlano, dataInicio));
    }
}

package com.example.cinestream.genero;

import org.springframework.stereotype.Service;

import com.example.cinestream.common.GenericCrudService;

@Service
public class GeneroService extends GenericCrudService<Genero, Integer> {

    public GeneroService(GeneroRepository repository) {
        super(repository, "Genero");
    }
}

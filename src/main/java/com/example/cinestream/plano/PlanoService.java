package com.example.cinestream.plano;

import org.springframework.stereotype.Service;

import com.example.cinestream.common.GenericCrudService;

@Service
public class PlanoService extends GenericCrudService<Plano, Integer> {

    public PlanoService(PlanoRepository repository) {
        super(repository, "Plano");
    }
}

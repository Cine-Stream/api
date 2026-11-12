package com.example.cinestream.obra;

import org.springframework.stereotype.Service;

import com.example.cinestream.common.GenericCrudService;

@Service
public class ObraService extends GenericCrudService<Obra, Integer> {

    public ObraService(ObraRepository repository) {
        super(repository, "Obra");
    }
}

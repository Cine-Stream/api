package com.example.cinestream.dispositivo;

import org.springframework.stereotype.Service;

import com.example.cinestream.common.GenericCrudService;

@Service
public class DispositivoService extends GenericCrudService<Dispositivo, Integer> {

    public DispositivoService(DispositivoRepository repository) {
        super(repository, "Dispositivo");
    }
}

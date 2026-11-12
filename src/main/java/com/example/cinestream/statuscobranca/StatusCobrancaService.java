package com.example.cinestream.statuscobranca;

import org.springframework.stereotype.Service;

import com.example.cinestream.common.GenericCrudService;

@Service
public class StatusCobrancaService extends GenericCrudService<StatusCobranca, Integer> {

    public StatusCobrancaService(StatusCobrancaRepository repository) {
        super(repository, "Status de cobranca");
    }
}

package com.example.cinestream.qualidaderesolucao;

import org.springframework.stereotype.Service;

import com.example.cinestream.common.GenericCrudService;

@Service
public class QualidadeResolucaoService extends GenericCrudService<QualidadeResolucao, Integer> {

    public QualidadeResolucaoService(QualidadeResolucaoRepository repository) {
        super(repository, "Qualidade de resolucao");
    }
}

package com.example.cinestream.visualizacao;

import org.springframework.stereotype.Service;

import com.example.cinestream.common.GenericCrudService;

@Service
public class VisualizacaoService extends GenericCrudService<Visualizacao, Integer> {

    public VisualizacaoService(VisualizacaoRepository repository) {
        super(repository, "Visualizacao");
    }
}

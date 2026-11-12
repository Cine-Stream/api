package com.example.cinestream.assinante;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.cinestream.common.GenericCrudService;

@Service
public class AssinanteService extends GenericCrudService<Assinante, Integer> {

    private final AssinanteRepository repository;

    public AssinanteService(AssinanteRepository repository) {
        super(repository, "Assinante");
        this.repository = repository;
    }
    //CACHE ASIDE
    @Cacheable("assinantes")
    public List<AssinanteResponse> listarComCache() {
        return repository.findAll()
                .stream()
                .map(AssinanteResponse::fromEntity)
                .toList();
    }

    @Override
    @CacheEvict(value = "assinantes", allEntries = true)
    public Assinante criar(Assinante assinante) {
        return super.criar(assinante);
    }

    @Override
    @CacheEvict(value = "assinantes", allEntries = true)
    public Assinante atualizar(Integer id, Assinante assinante) {
        return super.atualizar(id, assinante);
    }

    @Override
    @CacheEvict(value = "assinantes", allEntries = true)
    public void excluir(Integer id) {
        super.excluir(id);
    }
}

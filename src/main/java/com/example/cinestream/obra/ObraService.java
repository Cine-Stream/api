package com.example.cinestream.obra;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.cinestream.common.GenericCrudService;

import java.util.List;

@Service
public class ObraService extends GenericCrudService<Obra, Integer> {

    private final ObraRepository repository;

    public ObraService(ObraRepository repository) {
        super(repository, "Obra");
        this.repository = repository;
    }

    //CACHE ASIDE
    @Cacheable("obra")
    public List<ObraResponse> listarComCache() {
        return repository.findAll()
                .stream()
                .map(ObraResponse::fromEntity)
                .toList();
    }

    // INVALIDAÇÃO DO CACHE AO CRIAR
    @Override
    @CacheEvict(value = "obra", allEntries = true)
    public Obra criar(Obra entity) {
        return super.criar(entity);
    }

    // INVALIDAÇÃO DO CACHE AO ATUALIZAR
    @Override
    @CacheEvict(value = "obra", allEntries = true)
    public Obra atualizar(Integer id, Obra entity) {
        return super.atualizar(id, entity);
    }

    // INVALIDAÇÃO DO CACHE AO EXCLUIR
    @Override
    @CacheEvict(value = "obra", allEntries = true)
    public void excluir(Integer id) {
        super.excluir(id);
    }

}

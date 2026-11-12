package com.example.cinestream.assinatura;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cinestream.assinante.Assinante;
import com.example.cinestream.assinante.AssinanteRepository;
import com.example.cinestream.common.ResourceNotFoundException;
import com.example.cinestream.plano.Plano;
import com.example.cinestream.plano.PlanoRepository;

/**
 * Assinaturas sao registros de historico: a chave e composta por
 * (id_assinante, id_plano, data_inicio).
 */
@Service
public class AssinaturaService {

    private final AssinaturaRepository repository;
    private final AssinanteRepository assinanteRepository;
    private final PlanoRepository planoRepository;

    public AssinaturaService(
            AssinaturaRepository repository,
            AssinanteRepository assinanteRepository,
            PlanoRepository planoRepository) {
        this.repository = repository;
        this.assinanteRepository = assinanteRepository;
        this.planoRepository = planoRepository;
    }

    public List<Assinatura> listar() {
        return repository.findAll();
    }

    public Assinatura buscarPorId(AssinaturaId id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Assinatura", id.getAssinanteId()
                        + "/" + id.getPlanoId() + "/" + id.getDataInicio()));
    }

    @Transactional
    public Assinatura criar(AssinaturaRequest request) {
        Assinante assinante = assinanteRepository.findById(request.idAssinante())
                .orElseThrow(() -> new ResourceNotFoundException("Assinante", request.idAssinante()));
        Plano plano = planoRepository.findById(request.idPlano())
                .orElseThrow(() -> new ResourceNotFoundException("Plano", request.idPlano()));

        Assinatura assinatura = new Assinatura();
        assinatura.setId(new AssinaturaId(assinante.getId(), plano.getId(), request.dataInicio()));
        assinatura.setAssinante(assinante);
        assinatura.setPlano(plano);
        assinatura.setDataFim(request.dataFim());
        assinatura.setValorCobrado(request.valorCobrado());
        return repository.save(assinatura);
    }

    @Transactional
    public Assinatura atualizar(AssinaturaId id, AssinaturaUpdateRequest request) {
        Assinatura assinatura = buscarPorId(id);
        assinatura.setDataFim(request.dataFim());
        assinatura.setValorCobrado(request.valorCobrado());
        return repository.save(assinatura);
    }

    public void excluir(AssinaturaId id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Assinatura", id.getAssinanteId()
                    + "/" + id.getPlanoId() + "/" + id.getDataInicio());
        }
        repository.deleteById(id);
    }
}

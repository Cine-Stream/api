package com.example.cinestream.perfil;

import java.io.IOException;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.example.cinestream.armazenamento.ArmazenamentoS3Service;
import com.example.cinestream.armazenamento.S3Properties;
import com.example.cinestream.common.ResourceNotFoundException;

/**
 * Guarda a imagem do avatar no S3 e mantem apenas a URL em perfil.avatar_url
 * (Neon). A chave do objeto e deterministica, entao trocar o avatar sobrescreve
 * o arquivo anterior sem deixar lixo no bucket.
 */
@Service
public class PerfilAvatarService {

    private static final Set<String> TIPOS_ACEITOS = Set.of(
            "image/png", "image/jpeg", "image/jpg", "image/webp");

    private final PerfilRepository perfilRepository;
    private final ArmazenamentoS3Service armazenamento;
    private final S3Properties s3Properties;

    public PerfilAvatarService(
            PerfilRepository perfilRepository,
            ArmazenamentoS3Service armazenamento,
            S3Properties s3Properties) {
        this.perfilRepository = perfilRepository;
        this.armazenamento = armazenamento;
        this.s3Properties = s3Properties;
    }

    @Transactional
    public Perfil atualizar(PerfilId id, MultipartFile arquivo) {
        exigirS3Configurado();

        if (arquivo == null || arquivo.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Envie um arquivo no campo 'arquivo'.");
        }

        String contentType = arquivo.getContentType() == null
                ? ""
                : arquivo.getContentType().toLowerCase();
        if (!TIPOS_ACEITOS.contains(contentType)) {
            throw new ResponseStatusException(HttpStatus.UNSUPPORTED_MEDIA_TYPE,
                    "Formato nao suportado. Use PNG, JPEG ou WebP.");
        }

        if (arquivo.getSize() > s3Properties.getMaxBytes()) {
            throw new ResponseStatusException(HttpStatus.PAYLOAD_TOO_LARGE,
                    "Arquivo maior que o limite de " + s3Properties.getMaxBytes() + " bytes.");
        }

        Perfil perfil = buscarPerfil(id);
        String chave = armazenamento.chaveAvatar(id.getAssinanteId(), id.getNumeroSequencial());

        byte[] conteudo;
        try {
            conteudo = arquivo.getBytes();
        } catch (IOException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Nao foi possivel ler o arquivo enviado.");
        }

        perfil.setAvatarUrl(armazenamento.enviar(chave, conteudo, contentType));
        return perfilRepository.save(perfil);
    }

    @Transactional
    public void remover(PerfilId id) {
        exigirS3Configurado();

        Perfil perfil = buscarPerfil(id);
        armazenamento.remover(armazenamento.chaveAvatar(id.getAssinanteId(), id.getNumeroSequencial()));
        perfil.setAvatarUrl(null);
        perfilRepository.save(perfil);
    }

    private Perfil buscarPerfil(PerfilId id) {
        return perfilRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Perfil",
                        id.getAssinanteId() + "/" + id.getNumeroSequencial()));
    }

    private void exigirS3Configurado() {
        if (!armazenamento.estaConfigurado()) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE,
                    "Upload de avatar indisponivel: configure S3_BUCKET e as credenciais da AWS.");
        }
    }
}

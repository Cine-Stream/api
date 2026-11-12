package com.example.cinestream.armazenamento;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import software.amazon.awssdk.core.exception.SdkException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class ArmazenamentoS3Service {

    private static final Logger log = LoggerFactory.getLogger(ArmazenamentoS3Service.class);

    private final S3Client s3Client;
    private final S3Properties properties;

    public ArmazenamentoS3Service(S3Client s3Client, S3Properties properties) {
        this.s3Client = s3Client;
        this.properties = properties;
    }

    public boolean estaConfigurado() {
        return properties.isConfigurado();
    }

    public String chaveAvatar(Integer assinanteId, Integer numeroSequencial) {
        return "%d/%d/avatar".formatted(assinanteId, numeroSequencial);
    }

    public String enviar(String chave, byte[] conteudo, String contentType) {
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(properties.getBucket())
                .key(chave)
                .contentType(contentType)
                .cacheControl("public, max-age=86400")
                .build();

        try {
            s3Client.putObject(request, RequestBody.fromBytes(conteudo));
        } catch (SdkException exception) {
            log.error("Falha ao enviar o objeto {} para o bucket {}", chave, properties.getBucket(), exception);
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Falha ao enviar arquivo para o S3");
        }

        return montarUrlPublica(chave);
    }

    public void remover(String chave) {
        DeleteObjectRequest request = DeleteObjectRequest.builder()
                .bucket(properties.getBucket())
                .key(chave)
                .build();

        try {
            s3Client.deleteObject(request);
        } catch (SdkException exception) {
            log.error("Falha ao remover o objeto {} do bucket {}", chave, properties.getBucket(), exception);
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Falha ao remover arquivo do S3");
        }
    }

    private String montarUrlPublica(String chave) {
        if (StringUtils.hasText(properties.getPublicBaseUrl())) {
            return semBarraFinal(properties.getPublicBaseUrl()) + "/" + chave;
        }
        if (StringUtils.hasText(properties.getEndpoint())) {
            return semBarraFinal(properties.getEndpoint()) + "/" + properties.getBucket() + "/" + chave;
        }
        return "https://%s.s3.%s.amazonaws.com/%s"
                .formatted(properties.getBucket(), properties.getRegion(), chave);
    }

    private String semBarraFinal(String valor) {
        return valor.endsWith("/") ? valor.substring(0, valor.length() - 1) : valor;
    }
}

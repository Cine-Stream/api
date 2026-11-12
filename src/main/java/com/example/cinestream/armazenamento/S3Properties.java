package com.example.cinestream.armazenamento;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Configuracao do bucket S3 usado para guardar arquivos (avatares de perfil).
 * Sem bucket configurado a API continua funcionando; os endpoints de upload
 * respondem 503.
 */
@Component
@ConfigurationProperties(prefix = "app.s3")
public class S3Properties {

    private String bucket = "";
    private String region = "us-east-2";
    private String endpoint = "";
    private String publicBaseUrl = "";
    private String accessKeyId = "";
    private String secretAccessKey = "";
    private String sessionToken = "";
    private long maxBytes = 5L * 1024 * 1024;

    public boolean isConfigurado() {
        return bucket != null && !bucket.isBlank();
    }

    public boolean temCredenciaisEstaticas() {
        return accessKeyId != null && !accessKeyId.isBlank()
                && secretAccessKey != null && !secretAccessKey.isBlank();
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getPublicBaseUrl() {
        return publicBaseUrl;
    }

    public void setPublicBaseUrl(String publicBaseUrl) {
        this.publicBaseUrl = publicBaseUrl;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getSecretAccessKey() {
        return secretAccessKey;
    }

    public void setSecretAccessKey(String secretAccessKey) {
        this.secretAccessKey = secretAccessKey;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public long getMaxBytes() {
        return maxBytes;
    }

    public void setMaxBytes(long maxBytes) {
        this.maxBytes = maxBytes;
    }
}

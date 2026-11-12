package com.example.cinestream.armazenamento;

import java.net.URI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3ClientBuilder;
import software.amazon.awssdk.services.s3.S3Configuration;

@Configuration
public class S3Config {

    /**
     * As credenciais seguem a cadeia padrao da AWS (variaveis AWS_ACCESS_KEY_ID /
     * AWS_SECRET_ACCESS_KEY, perfil ~/.aws/credentials ou IAM Role).
     */
    @Bean
    public S3Client s3Client(S3Properties properties) {
        S3ClientBuilder builder = S3Client.builder()
                .region(Region.of(properties.getRegion()))
                .credentialsProvider(DefaultCredentialsProvider.create())
                // Necessario para S3 compativel (MinIO/LocalStack) que nao devolve
                // o checksum na resposta; nao afeta o upload para a AWS.
                .serviceConfiguration(S3Configuration.builder()
                        .checksumValidationEnabled(false)
                        .build());

        if (StringUtils.hasText(properties.getEndpoint())) {
            builder.endpointOverride(URI.create(properties.getEndpoint()))
                    .forcePathStyle(true);
        }

        return builder.build();
    }
}

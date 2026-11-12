package com.example.cinestream.perfil;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/perfis")
public class PerfilAvatarController {

    private final PerfilAvatarService service;

    public PerfilAvatarController(PerfilAvatarService service) {
        this.service = service;
    }

    @Operation(summary = "Envia o avatar do perfil para o bucket S3 e atualiza a URL no banco")
    @PostMapping(value = "/{assinanteId}/{numeroSequencial}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Perfil enviar(
            @PathVariable("assinanteId") Integer assinanteId,
            @PathVariable("numeroSequencial") Integer numeroSequencial,
            @RequestPart("arquivo") MultipartFile arquivo) {
        return service.atualizar(new PerfilId(assinanteId, numeroSequencial), arquivo);
    }

    @Operation(summary = "Remove o avatar do bucket S3 e limpa a URL no banco")
    @DeleteMapping("/{assinanteId}/{numeroSequencial}/avatar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(
            @PathVariable("assinanteId") Integer assinanteId,
            @PathVariable("numeroSequencial") Integer numeroSequencial) {
        service.remover(new PerfilId(assinanteId, numeroSequencial));
    }
}

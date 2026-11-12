package com.example.cinestream.assinatura;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

/**
 * Dados que podem ser alterados em um registro de historico de assinatura.
 * A chave (id_assinante, id_plano, data_inicio) vem da URL.
 */
public record AssinaturaUpdateRequest(
        LocalDate dataFim,
        @NotNull @DecimalMin(value = "0.00") BigDecimal valorCobrado) {
}

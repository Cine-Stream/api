package com.example.cinestream.assinatura;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record AssinaturaRequest(
        @NotNull Integer idAssinante,
        @NotNull Integer idPlano,
        @NotNull LocalDate dataInicio,
        LocalDate dataFim,
        @NotNull @DecimalMin(value = "0.00") BigDecimal valorCobrado) {
}

package com.productos.productos.dto;

import java.math.BigDecimal;

import com.productos.productos.exception.ErrorCampoVacioONulo;

import jakarta.validation.constraints.NotBlank;

public record ProductoRequest(
    @NotBlank(message = ErrorCampoVacioONulo.ERROR_CAMPO_VACIO_NULO)
    String nombre,
    BigDecimal precio,
    Long idCat
) {
    
}

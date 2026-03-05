package com.productos.productos.dto;

import java.math.BigDecimal;

import com.productos.productos.exception.ErrorCampoVacioONulo;
import com.productos.productos.exception.ErrorPrecioInvalido;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record ProductoRequest(
    @NotBlank(message = ErrorCampoVacioONulo.ERROR_CAMPO_VACIO_NULO)
    String nombre,
    @PositiveOrZero(message = ErrorPrecioInvalido.ERROR_PRECIO_NEGATIVO )
    BigDecimal precio,
    @NotNull
    Long idCat
) {
    
}

package com.productos.productos.exception;

public class ErrorCampoVacioONulo extends RuntimeException{
    public static final String ERROR_CAMPO_VACIO_NULO = "El campo esta vacío o nulo.";

    public ErrorCampoVacioONulo(String mensaje){
        super(mensaje);
    }
}

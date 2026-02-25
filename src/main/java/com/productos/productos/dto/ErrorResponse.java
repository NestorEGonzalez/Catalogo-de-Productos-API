package com.productos.productos.dto;

public record ErrorResponse (
    int status,
    String mensaje,
    long timestamp
){
    
}

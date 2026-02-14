package com.productos.productos.exception;

public class ErrorCategoriaYaExistente extends RuntimeException {
    public ErrorCategoriaYaExistente(String nombre){
        super("La categoria "+ nombre + ", ya existe.");
    }
    
}

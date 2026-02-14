package com.productos.productos.exception;

public class ErrorCategoriaInexistente extends RuntimeException {
    
    public ErrorCategoriaInexistente(Long id){
        super("La categoria con id: "+id+", no existe.");
    }
}

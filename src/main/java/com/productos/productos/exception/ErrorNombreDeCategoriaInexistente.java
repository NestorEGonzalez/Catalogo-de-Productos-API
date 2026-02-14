package com.productos.productos.exception;

public class ErrorNombreDeCategoriaInexistente extends RuntimeException {
    public ErrorNombreDeCategoriaInexistente(String nombre){
        super("La categoria: "+nombre+", no existe.");
    }
}

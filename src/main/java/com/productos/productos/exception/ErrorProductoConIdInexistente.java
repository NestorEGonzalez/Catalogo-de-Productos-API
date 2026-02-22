package com.productos.productos.exception;

public class ErrorProductoConIdInexistente extends RuntimeException{
    public ErrorProductoConIdInexistente(Long idProd){
        super("El producto con id "+idProd+", no existe.");
    }

}

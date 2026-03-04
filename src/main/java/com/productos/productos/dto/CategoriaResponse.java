package com.productos.productos.dto;

public class CategoriaResponse {
    Long id;
    String categoria;

    public CategoriaResponse(){}

    public CategoriaResponse(Long id, String categoria){
        this.id = id;
        this.categoria = categoria;
    }

    public Long getId(){
        return this.id;
    }

    public String getCategoria(){
        return this.categoria;
    }

    public void setId(Long id){
        this.id = id;
    }

    public void setCategoria(String categoria){
        this.categoria = categoria;
    }
}

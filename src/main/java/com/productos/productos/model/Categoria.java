package com.productos.productos.model;

import com.productos.productos.exception.ErrorCampoVacioONulo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = ErrorCampoVacioONulo.ERROR_CAMPO_VACIO_NULO)
    @Column(unique = true, nullable = false)
    private String categoria;

    public Categoria(){}

    public Categoria(String nombre){
        verificarCategoria(nombre);
        
        this.categoria = nombre.trim().toLowerCase();
    }

    public void setCategoria(String nuevoNombre){
        verificarCategoria(nuevoNombre);
        this.categoria = nuevoNombre.trim().toLowerCase();
    }

    private void verificarCategoria(String nuevoNombre) {
        if (nuevoNombre == null) {
            throw new ErrorCampoVacioONulo(ErrorCampoVacioONulo.ERROR_CAMPO_VACIO_NULO);   
        }
        if (nuevoNombre.isEmpty() || nuevoNombre.isBlank()) {
            throw new ErrorCampoVacioONulo(ErrorCampoVacioONulo.ERROR_CAMPO_VACIO_NULO);
        }
        
    }

    public String getCategoria(){
        return this.categoria;
    }

    public Long getId(){
        return this.id;
    }

    
    
}

package com.productos.productos.model;

import java.math.BigDecimal;

import com.productos.productos.exception.ErrorCampoVacioONulo;
import com.productos.productos.exception.ErrorPrecioInvalido;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity

@Table(name="producto")

public class Producto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nombre;

    private BigDecimal precio;
    
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;

    public Producto(){}

    public Producto(String nombre, BigDecimal precio, Categoria categoria){
        validarNombre(nombre);
        validarPrecio(precio);
        this.nombre = nombre.trim().toLowerCase();
        this.precio = precio;
        this.categoria = categoria;
    }

    public String getNombre(){
        return this.nombre;
    }

    public BigDecimal getPrecio(){
        return this.precio;
    }

    public String getCategoria(){
        return this.categoria != null ? this.categoria.getCategoria(): null;
    }

    public void setNombre(String nombre){
        validarNombre(nombre);
        this.nombre = nombre;

    }

    private void validarNombre(String nombre) {
        if (nombre == null || nombre.isEmpty() || nombre.isBlank()) {
            throw new ErrorCampoVacioONulo(ErrorCampoVacioONulo.ERROR_CAMPO_VACIO_NULO);
        }
        
    }

    public void setPrecio(BigDecimal precio){
        validarPrecio(precio);
        this.precio = precio;
    }

    private void validarPrecio(BigDecimal precio) {
        if (precio == null) {
            throw new ErrorPrecioInvalido(ErrorPrecioInvalido.ERROR_PRECIO_NULO);
        }
        BigDecimal cero = new BigDecimal("0");
        if (precio.compareTo(cero)<0) {
            throw new ErrorPrecioInvalido(ErrorPrecioInvalido.ERROR_PRECIO_NEGATIVO);
        }
    }

    public void setCategoria(Categoria categoria){
        this.categoria = categoria;
    }

    public Long getId() {
        return this.id;
    }
}
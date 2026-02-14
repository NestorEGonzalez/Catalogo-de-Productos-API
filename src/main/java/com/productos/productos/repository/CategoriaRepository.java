package com.productos.productos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.productos.productos.model.Categoria;


public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Optional <Categoria> findOneByCategoria(String nombreCategoria);

    boolean existsByNombre(String nombre);

    
} 

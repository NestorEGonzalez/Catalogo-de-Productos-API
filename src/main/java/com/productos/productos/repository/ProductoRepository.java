package com.productos.productos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.productos.productos.model.Categoria;
import com.productos.productos.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByCategoria(Categoria categoria);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Producto p SET p.categoria = :nuevaCategoria WHERE p.categoria = :categoria")
    int actualizarCategoria(@Param("nuevaCategoria")Categoria nuevaCategoria,@Param("categoria") Categoria categoria);

    @Query("SELECT p from Producto p JOIN FETCH p.categoria WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%',:nombre,'%'))")    
    List<Producto> buscarPorNombreConCategoria(@Param("nombre") String nombre);
    //List<Producto> findByNombreContainingIgnoreCase(String nombre);

    Optional<Producto> findOneByNombreIgnoreCase(String nombre);
    
    //@Query("SELECT Producto p WHERE p.nombre LIKE '%:nombre%'")
    //List<Producto> buscarProductosQueContenga(@Param("nombre") String nombre);
    
}

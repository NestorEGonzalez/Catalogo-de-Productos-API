package com.productos.productos.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.productos.productos.dto.CategoriaRequest;
import com.productos.productos.model.Categoria;
import com.productos.productos.service.CategoriaService;

import jakarta.validation.Valid;

@RestController
public class CategoriaController {
    private final CategoriaService categoriaService;
    
    public CategoriaController(CategoriaService categoriaService){
        this.categoriaService = categoriaService;
    }

    @GetMapping("/productos/categorias")
    public ResponseEntity<List<Categoria>> obtenerCategorias(){
        List<Categoria> listaDeCategorias = categoriaService.obtenerTodasLasCategorias();
        return ResponseEntity.ok(listaDeCategorias);
    }

    @PostMapping("/productos/categorias")
    public ResponseEntity<Categoria> crearCategoria(@Valid @RequestBody CategoriaRequest categoria ){
        Categoria cat = categoriaService.crearCategoria(categoria.categoria());
        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("{id}")
                        .buildAndExpand(cat.getId())
                        .toUri();
        return ResponseEntity.created(location).body(cat);
    }

    @DeleteMapping("/productos/categorias/{id}")
    public ResponseEntity<HttpStatus> borrarCategoria(@PathVariable Long id){
        categoriaService.eliminarCategoria(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/productos/categorias/{id}")
    public ResponseEntity<Categoria> modificarCategoria(@PathVariable Long id,@Valid @RequestBody CategoriaRequest categoria){
        Categoria cat = categoriaService.cambiarNombreDeCategoriaPorId(id, categoria.categoria());
        return ResponseEntity.ok(cat);
    }

    @GetMapping("/productos/categorias/filtrar/{cat}")
    public ResponseEntity<List<Categoria>> filtrarCategorias(@Valid @PathVariable String cat){
        List<Categoria> cats = categoriaService.buscarCategoriasQueContengan(cat);
        return ResponseEntity.ok(cats);
    }

    @GetMapping("/productos/categorias/{id}")
    public ResponseEntity<Categoria> buscarCategoriaPorId(@PathVariable Long id){
        Categoria cat = categoriaService.buscarCategoriaPorId(id);
        return ResponseEntity.ok(cat);
    }
}

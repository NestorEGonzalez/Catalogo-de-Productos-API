package com.productos.productos.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.productos.productos.dto.ProductoRequest;
import com.productos.productos.dto.ProductoResponse;
import com.productos.productos.mapper.ProductoMapper;
import com.productos.productos.model.Producto;
import com.productos.productos.service.ProductoService;

@RestController
public class ProductoController {
    private final ProductoService productoService;
    private final ProductoMapper productoMapper;

    public ProductoController(ProductoService productoService, ProductoMapper productoMapper){
        this.productoService = productoService;
        this.productoMapper = productoMapper;
    
    }

    @PostMapping("/productos/productos")
    public ResponseEntity<ProductoResponse> crearProducto(@RequestBody ProductoRequest producto){
        Producto prod = productoService.crearProducto(
                                        producto.nombre(),
                                        producto.precio(),
                                        producto.idCat());
        ProductoResponse productoFinal = productoMapper.toResponse(prod);

        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("{id}")
                        .buildAndExpand(prod.getId())
                        .toUri();
        return ResponseEntity.created(location).body(productoFinal);
    }

    @GetMapping("/productos/productos")
    public ResponseEntity<List<ProductoResponse>> obtenerTodosLosProductos(){
        List<ProductoResponse> listaDeProductos = productoMapper.toResponseList(productoService.obtenerTodosLosProductos());

        return ResponseEntity.ok(listaDeProductos);
    }
}

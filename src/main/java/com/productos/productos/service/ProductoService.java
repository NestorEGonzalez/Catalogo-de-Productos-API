package com.productos.productos.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.productos.productos.exception.ErrorCampoVacioONulo;
import com.productos.productos.exception.ErrorCategoriaInexistente;
import com.productos.productos.exception.ErrorNombreProductoExistente;
import com.productos.productos.exception.ErrorProductoConIdInexistente;
import com.productos.productos.model.Categoria;
import com.productos.productos.model.Producto;
import com.productos.productos.repository.CategoriaRepository;
import com.productos.productos.repository.ProductoRepository;

@Service
@Transactional(readOnly = true)

public class ProductoService {

    private ProductoRepository productoRepository;

    private CategoriaRepository categoriaRepository;

    public ProductoService(ProductoRepository productoRepository, CategoriaRepository categoriaRepository){
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional
    public Producto crearProducto(String nombreProd, BigDecimal precio, Long idCat) {
        verificarNombre(nombreProd);
        Categoria cat = obtenerCategoriaPorId(idCat);
        
        return productoRepository.save(new Producto(nombreProd,precio,cat));
        
    }

    private Categoria obtenerCategoriaPorId(Long idCat) {
        Categoria cat = categoriaRepository
                        .findById(idCat)
                        .orElseThrow(()->{
                            throw new ErrorCategoriaInexistente(idCat);
                        });
        return cat;
    }

    private void verificarNombre(String nombreProd) {
        if (nombreProd == null) {
            throw new ErrorCampoVacioONulo(ErrorCampoVacioONulo.ERROR_CAMPO_VACIO_NULO);
        }
        if (productoRepository.existsByNombre(nombreProd.trim().toLowerCase())) {
            throw new ErrorNombreProductoExistente(nombreProd.trim().toLowerCase());
        }
    }

    @Transactional
    public void borrarProductoConId(Long idProd) {
        verificarProducto(idProd);
        productoRepository.deleteById(idProd);
    }

    private void verificarProducto(Long idProd) {
        if (!productoRepository.existsById(idProd)) {
            throw new ErrorProductoConIdInexistente(idProd);
        }
    }

    @Transactional
    public void cambiarNombreDeProducto(Long idProd, String nombreNuevo) {
        Producto producto = obtenerProductoConId(idProd);
        verificarNombre(nombreNuevo);
        producto.setNombre(nombreNuevo);
        productoRepository.save(producto);
    }

    private Producto obtenerProductoConId(Long idProd) {
        Producto producto = productoRepository
                            .findById(idProd)
                            .orElseThrow(()->{
                                throw new ErrorProductoConIdInexistente(idProd);
                            });
        return producto;                    
    }

    @Transactional
    public void cambiarCategoriaDeProducto(Long idProd, Long idCat) {
        Producto producto = obtenerProductoConId(idProd);
        Categoria cat = obtenerCategoriaPorId(idCat);
        producto.setCategoria(cat);
        productoRepository.save(producto);
    }

    public void cambiarPrecioDeProducto(Long idProd, BigDecimal precioNuevo) {
        Producto producto = obtenerProductoConId(idProd);
        producto.setPrecio(precioNuevo);
    }

    public List<Producto>obtenerTodosLosProductos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> buscarProductoConId(Long id) {
        return productoRepository.findById(id);
    }

    public List<Producto> obtenerProductosDeCategoria(Long id) {
        Categoria cat = obtenerCategoriaPorId(id);
        return productoRepository.findAllByCategoria(cat);
    }

    public void actualizarCategoriaDeProductos(Long id, Long id2) {
        Categoria cat1 = obtenerCategoriaPorId(id);
        Categoria cat2 = obtenerCategoriaPorId(id2);
        productoRepository.actualizarCategoria(cat2, cat1);
        
    }

    public List<Producto> obtenerProductosConNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre);

    }
    
}

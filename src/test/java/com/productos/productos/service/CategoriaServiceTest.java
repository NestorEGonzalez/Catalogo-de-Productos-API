package com.productos.productos.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.productos.productos.model.Categoria;
import com.productos.productos.repository.CategoriaRepository;

@ExtendWith(MockitoExtension.class)

public class CategoriaServiceTest{
    
    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private CategoriaService categoriaService;


    @Test
    void test01_sePuedeGuardarUnaCategoriaYSeRetonarLaCategoriaGuardada(){

        String nombreCategoria = "Prueba";
        Categoria categoria = new Categoria(nombreCategoria);

        //Simulacion de guardar en el repositorio
        when(categoriaRepository.save(any(Categoria.class))).thenReturn(categoria);

        Categoria categoriaDelService = categoriaService.crearCategoria(nombreCategoria);

        assertNotNull(categoriaDelService);
        assertEquals(nombreCategoria, categoriaDelService.getCategoria());

        verify(categoriaRepository, times(1)).save(any(Categoria.class));
    }

    @Test
    void test02_alGuardarUnaCategoriaExistenteSeLanzaUnError(){

        String nombreCategoria = "Prueba";
        Categoria categoria = new Categoria(nombreCategoria);
        


    }
}

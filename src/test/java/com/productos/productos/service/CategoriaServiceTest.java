package com.productos.productos.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.productos.productos.exception.ErrorCategoriaInexistente;
import com.productos.productos.exception.ErrorCategoriaYaExistente;
import com.productos.productos.model.Categoria;
import com.productos.productos.repository.CategoriaRepository;

@ExtendWith(MockitoExtension.class)

public class CategoriaServiceTest{
    
    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private CategoriaService categoriaService;

    String nombreCategoria;
    Categoria categoria;

    @BeforeEach
    void setUp(){
        nombreCategoria = "Prueba";
        categoria = new Categoria(nombreCategoria);
    }


    @Test
    void test01_sePuedeGuardarUnaCategoriaYSeRetonarLaCategoriaGuardada(){

        when(categoriaRepository.existsByNombre(nombreCategoria)).thenReturn(false);

        when(categoriaRepository.save(any(Categoria.class))).thenReturn(categoria);

        Categoria categoriaDelService = categoriaService.crearCategoria(nombreCategoria);

        assertNotNull(categoriaDelService);
        assertEquals(nombreCategoria, categoriaDelService.getCategoria());

        verify(categoriaRepository, times(1)).save(any(Categoria.class));
    }

    @Test
    void test02_alGuardarUnaCategoriaExistenteSeLanzaUnError(){

        when(categoriaRepository.existsByNombre(nombreCategoria)).thenReturn(true);

        assertThrows(ErrorCategoriaYaExistente.class,()->{
            categoriaService.crearCategoria(nombreCategoria);
        });

    }

    @Test
    void test03_sePuedeObtenerUnaCategoriaPorSuNombre(){

        when(categoriaRepository.findOneByCategoria(nombreCategoria)).thenReturn(Optional.of(categoria));
    
        Optional<Categoria> catEncontrada = categoriaService.buscarCategoriaPorNombre(nombreCategoria);

        assertNotNull(catEncontrada);
        assertEquals(nombreCategoria,catEncontrada.get().getCategoria());
        
    }

    @Test
    void test04_siElNombreDeUnaCategoriaNoExisteObtengoNull(){
        
        //when(categoriaRepository.existsByNombre(nombreCategoria)).thenReturn(false);
    
        Optional<Categoria> catEncontrada = categoriaService.buscarCategoriaPorNombre(nombreCategoria);

        //assertNull(catEncontrada);
        assertFalse(catEncontrada.isPresent());
    }

    @Test
    void test05_sePuedeBorrarUnaCategoriaExistente(){
        
        Long id = 1L;

        when(categoriaRepository.existsById(id)).thenReturn(true);

        categoriaService.eliminarCategoria(id);

        verify(categoriaRepository).deleteById(id);;
        
    }

    @Test
    void test06_noSePuedeBorrarUnaCategoriaInexistente(){
        Long id = 1L;

        when(categoriaRepository.existsById(id)).thenReturn(false);

        assertThrows(ErrorCategoriaInexistente.class, ()->{
            categoriaService.eliminarCategoria(id);
        });

        verify(categoriaRepository, never()).delete(any());

    }

    @Test
    void test07_sePuedeModificarElNombreDeUnaCategoriaExistente(){
        String nombreNuevo = "Nuevo";
        
        when(categoriaRepository.findOneByCategoria(nombreCategoria)).thenReturn(Optional.of(categoria));
        when(categoriaRepository.existsByNombre(nombreNuevo)).thenReturn(false);

        categoriaService.cambiarNombreDeCategoria(nombreCategoria,nombreNuevo);

        assertEquals(nombreNuevo, categoria.getCategoria());

        verify(categoriaRepository).save(categoria);

    }

    @Test
    void test08_noSePuedeModificarElNombreDeUnaCategoriaExistenteSiElNombreYaExiste(){
        String nuevoNombre = nombreCategoria;
        Categoria nuevaCat = new Categoria(nuevoNombre);
        when(categoriaRepository.existsByNombre(nombreCategoria)).thenReturn(true);
        when(categoriaRepository.existsByNombre(nuevoNombre)).thenReturn(true);

        assertThrows(RuntimeException.class,()->{
            categoriaService.cambiarNombreDeCategoria(nombreCategoria, nuevoNombre);
        });

        verify(categoriaRepository,never()).save(any());
    }

    @Test
    void test09_noSePuedeModificarElNombreDeUnaCategoriaQueNoExiste(){
        String nuevoNombre = "Nuevo";
        when(categoriaRepository.findOneByCategoria(nombreCategoria)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, ()->{
            categoriaService.cambiarNombreDeCategoria(nombreCategoria, nuevoNombre);
        });

        verify(categoriaRepository, never()).save(any());
    }

}

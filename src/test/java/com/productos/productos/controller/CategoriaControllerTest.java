package com.productos.productos.controller;

/* import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

//import com.fasterxml.jackson.databind.ObjectMapper;
import com.productos.productos.model.Categoria;
import com.productos.productos.service.CategoriaService;
import com.productos.productos.exception.ErrorCategoriaYaExistente;

import java.util.Optional;
import java.util.List;

//@WebMvcTest(CategoriaController.class) */
public class CategoriaControllerTest {
/* 
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoriaService categoriaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCrearCategoria() throws Exception {
        Categoria cat = new Categoria(1L, "Prueba");
        when(categoriaService.crearCategoria("Prueba")).thenReturn(cat);

        mockMvc.perform(post("/categorias")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cat)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Prueba"));
    }

    @Test
    void testNoCrearCategoriaDuplicada() throws Exception {
        when(categoriaService.crearCategoria("Prueba"))
                .thenThrow(new ErrorCategoriaYaExistente("La categoria prueba, ya existe."));

        Categoria cat = new Categoria(null, "Prueba");

        mockMvc.perform(post("/categorias")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cat)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensaje").value("La categoria prueba, ya existe."));
    }

    @Test
    void testObtenerTodasLasCategorias() throws Exception {
        when(categoriaService.obtenerTodasLasCategorias())
                .thenReturn(List.of(new Categoria(1L, "Prueba")));

        mockMvc.perform(get("/categorias"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    } */
}

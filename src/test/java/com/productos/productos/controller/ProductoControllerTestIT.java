package com.productos.productos.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.productos.productos.TestBase;
import com.productos.productos.dto.CategoriaRequest;
import com.productos.productos.dto.ProductoRequest;
import com.productos.productos.model.Categoria;


import jakarta.transaction.Transactional;
import tools.jackson.databind.ObjectMapper;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc(addFilters = false)

public class ProductoControllerTestIT extends TestBase {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    String nombreCat;
    String nombreProducto;
    String otroNombreProducto;
    BigDecimal precioProducto;
    private Categoria categoria;

    @BeforeEach
    void setUp() throws Exception{
        nombreCat = "Categoria de prueba";
        String catGuardada = mockMvc.perform(post("/productos/categorias")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(objectMapper.writeValueAsString(new CategoriaRequest(nombreCat))))
                                    .andExpect(status().isCreated())
                                    .andReturn().getResponse().getContentAsString();
        categoria = objectMapper.readValue(catGuardada, Categoria.class);
        nombreProducto = "Producto de prueba";
        otroNombreProducto = "Otro Producto de prueba";
        precioProducto = new BigDecimal("150");
    }
    
    @Test
    void test_sePuedeCrearUnProductoYSeObtieneUnHTTPStatus200() throws Exception{
        mockMvc.perform(post("/productos/productos")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(new ProductoRequest(nombreProducto, precioProducto, categoria.getId()))))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.id").exists())
                        .andExpect(jsonPath("$.id").isNumber())
                        .andExpect(jsonPath("$.nombre").value(nombreProducto.trim().toLowerCase()))
                        .andExpect(jsonPath("$.categoriaId").value((categoria.getId())))
                        .andExpect(jsonPath("$.precio").value(precioProducto))
                        .andExpect(header().exists("Location"));
    }

    @Test
    void test_sePuedenObtenerTodosLosProductosGuardados() throws Exception{
        mockMvc.perform(post("/productos/productos")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(new ProductoRequest(nombreProducto, precioProducto, categoria.getId()))))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.id").exists())
                        .andExpect(jsonPath("$.id").isNumber())
                        .andExpect(jsonPath("$.nombre").value(nombreProducto.trim().toLowerCase()))
                        .andExpect(jsonPath("$.categoriaId").value((categoria.getId())))
                        .andExpect(jsonPath("$.precio").value(precioProducto))
                        .andExpect(header().exists("Location"));
        mockMvc.perform(post("/productos/productos")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(new ProductoRequest(otroNombreProducto, precioProducto, categoria.getId()))))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.id").exists())
                        .andExpect(jsonPath("$.id").isNumber())
                        .andExpect(jsonPath("$.nombre").value(otroNombreProducto.trim().toLowerCase()))
                        .andExpect(jsonPath("$.categoriaId").value((categoria.getId())))
                        .andExpect(jsonPath("$.precio").value(precioProducto))
                        .andExpect(header().exists("Location"));
        
        mockMvc.perform(get("/productos/productos"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$",hasSize(2)))
                        .andExpect(jsonPath("$[0].nombre").value(nombreProducto.trim().toLowerCase()))
                        .andExpect(jsonPath("$[1].nombre").value(otroNombreProducto.trim().toLowerCase()));
    }

    @Test
    void test_noSePuedeCrearUnProductoConNombreNuloOVacio() throws Exception{}
    
    @Test
    void test_noSePuedeCrearUnProductoConPrecioNegativoONulo() throws Exception{}

    @Test 
    void test_noSePuedeCrearUnProductoConUnaCategoriaNuloOInexistente() throws Exception{}

    @Test
    void test_sePuedeEliminarUnProductoPorSuId() throws Exception{}

    @Test
    void test_noSeEliminaUnProductoConIdInexistente() throws Exception{}

    @Test
    void test_sePuedeModificarElNombreDeUnProducto() throws Exception{}

    

}

package com.productos.productos.service;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.productos.productos.model.Categoria;
import com.productos.productos.repository.CategoriaRepository;

//import jakarta.transaction.Transactional;

@Service
@Transactional(readOnly = true)
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository){
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional
    public Categoria crearCategoria(String categoria) {
        Categoria cat = new Categoria(categoria);
        return categoriaRepository.save(cat);
    }

}

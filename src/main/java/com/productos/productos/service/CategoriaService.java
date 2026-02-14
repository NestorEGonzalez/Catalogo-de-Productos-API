package com.productos.productos.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.productos.productos.exception.ErrorCategoriaInexistente;
import com.productos.productos.exception.ErrorCategoriaYaExistente;
import com.productos.productos.exception.ErrorNombreDeCategoriaInexistente;
import com.productos.productos.model.Categoria;
import com.productos.productos.repository.CategoriaRepository;

@Service
@Transactional(readOnly = true)
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository){
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional
    public Categoria crearCategoria(String nombreCategoria) {
        verificarCategoriaExistente(nombreCategoria);
        
        //Categoria cat = new Categoria(nombreCategoria);
        return categoriaRepository.save(new Categoria(nombreCategoria));
    }

    private void verificarCategoriaExistente(String nombre) {
        if (categoriaRepository.existsByNombre(nombre)) {
            throw new ErrorCategoriaYaExistente(nombre);
        }
        
    }

	public Optional<Categoria> buscarCategoriaPorNombre(String nombreCategoria) {
		return categoriaRepository.findOneByCategoria(nombreCategoria);
	}

    @Transactional
    public void eliminarCategoria(Long id) {
        verificarIdExistente(id);
        categoriaRepository.deleteById(id);;
    }

    private void verificarIdExistente(Long id) {
        if (!categoriaRepository.existsById(id)){
            throw new ErrorCategoriaInexistente(id);

        }
    }

    @Transactional
    public void cambiarNombreDeCategoria(String nombreActual, String nombreNuevo) {
        //verificarCategoriaExistente(nombreNuevo);
        verificarCategoriaExistente(nombreNuevo);
        Categoria cat = categoriaRepository
                            .findOneByCategoria(nombreActual)
                            .orElseThrow(()->{
                                throw new  ErrorNombreDeCategoriaInexistente(nombreActual);
                            });
        cat.setCategoria(nombreNuevo);
        categoriaRepository.save(cat);
    }

}

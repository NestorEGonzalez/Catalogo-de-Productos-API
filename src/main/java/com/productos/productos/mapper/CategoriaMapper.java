package com.productos.productos.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.productos.productos.dto.CategoriaResponse;
import com.productos.productos.model.Categoria;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    CategoriaResponse toResponse(Categoria categoria);

    List<CategoriaResponse> toResponse(List<Categoria> categorias);
} 

package com.productos.productos.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.productos.productos.dto.ProductoResponse;
import com.productos.productos.model.Producto;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    @Mapping(target = "categoriaId", source = "categoria.id")
    ProductoResponse toResponse(Producto producto);

    List<ProductoResponse> toResponseList(List<Producto> productos);
    
} 

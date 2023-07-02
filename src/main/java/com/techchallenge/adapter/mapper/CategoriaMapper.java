package com.techchallenge.adapter.mapper;


import java.util.Collection;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techchallenge.adapter.driver.model.CategoriaModel;
import com.techchallenge.adapter.driver.model.input.CategoriaInput;
import com.techchallenge.core.domain.Categoria;

@Component
public class CategoriaMapper {

    @Autowired
    private ModelMapper mapper;

    public Categoria toDomainObject(CategoriaInput input) {
        return mapper.map(input, Categoria.class);
    }

    public CategoriaModel toModel(Categoria categoria) {
        return mapper.map(categoria, CategoriaModel.class);
    }

    public Collection<CategoriaModel> toCollectionModel(Collection<Categoria> categorias) {
        return categorias.stream()
                .map(c -> mapper.map(c, CategoriaModel.class))
                .collect(Collectors.toList());
    }
}
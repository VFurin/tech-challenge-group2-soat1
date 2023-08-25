package com.techchallenge.adapter.mapper.business;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techchallenge.core.domain.entities.Categoria;
import com.techchallenge.drivers.db.entities.CategoriaEntity;

@Component
public class CategoriaBusinessMapper {

    @Autowired
    private ModelMapper mapper;

    public List<Categoria> toCollectionModel(Collection<CategoriaEntity> entities) {
        return entities.stream()
                .map(c -> mapper.map(c, Categoria.class))
                .collect(Collectors.toList());
    }
}
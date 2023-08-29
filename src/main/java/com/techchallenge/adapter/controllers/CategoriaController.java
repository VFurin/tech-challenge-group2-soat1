package com.techchallenge.adapter.controllers;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techchallenge.adapter.driver.model.CategoriaModel;
import com.techchallenge.adapter.mapper.api.CategoriaApiMapper;
import com.techchallenge.core.domain.entities.Categoria;
import com.techchallenge.core.domain.usecases.CategoriaUseCase;

@Component
public class CategoriaController {

	@Autowired
	private CategoriaUseCase useCase;

    @Autowired
    private CategoriaApiMapper mapper;
	
    public Collection<CategoriaModel> listar() {
        List<Categoria> todasCategorias = useCase.buscarTodos();
        return mapper.toCollectionModel(todasCategorias);
    }
}

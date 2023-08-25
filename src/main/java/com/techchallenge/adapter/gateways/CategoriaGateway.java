package com.techchallenge.adapter.gateways;

import java.util.List;

import com.techchallenge.core.domain.entities.Categoria;



public interface CategoriaGateway {

	List<Categoria> buscarTodos();
	Categoria buscarPorId(Long id);
}

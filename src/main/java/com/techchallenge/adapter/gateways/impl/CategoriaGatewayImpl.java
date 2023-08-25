package com.techchallenge.adapter.gateways.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techchallenge.adapter.gateways.CategoriaGateway;
import com.techchallenge.adapter.mapper.business.CategoriaBusinessMapper;
import com.techchallenge.core.domain.entities.Categoria;
import com.techchallenge.drivers.db.entities.CategoriaEntity;
import com.techchallenge.drivers.db.repositories.CategoriaRepository;

@Component
public class CategoriaGatewayImpl implements CategoriaGateway {

    @Autowired
    private CategoriaRepository repository;

    @Autowired
    private CategoriaBusinessMapper mapper;

    public List<Categoria> buscarTodos() {
    	List<CategoriaEntity> entities = repository.findAll();
    	return mapper.toCollectionModel(entities);
    }
}

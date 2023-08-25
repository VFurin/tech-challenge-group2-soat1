package com.techchallenge.core.applications.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.techchallenge.drivers.db.entities.CategoriaEntity;
import com.techchallenge.drivers.db.repositories.CategoriaRepository;

//@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public List<CategoriaEntity> buscarTodos() {
        return repository.findAll();
    }
}

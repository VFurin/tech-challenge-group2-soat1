package com.techchallenge.core.applications.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techchallenge.core.applications.ports.CategoriaRepository;
import com.techchallenge.core.domain.Categoria;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public List<Categoria> buscarTodos() {
        return repository.findAll();
    }
}

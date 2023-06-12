package com.techchallenge.core.applications.service;

import com.techchallenge.core.applications.ports.CategoriaRepository;
import com.techchallenge.core.domain.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    @Transactional
    public Categoria salvar(Categoria categoria) {return repository.save(categoria);}
}

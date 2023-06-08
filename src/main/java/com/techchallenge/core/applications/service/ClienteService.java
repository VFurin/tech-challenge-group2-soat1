package com.techchallenge.core.applications.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techchallenge.core.applications.ports.ClienteRepository;
import com.techchallenge.core.domain.Cliente;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Transactional
    public Cliente salvar(Cliente cliente) {
        return repository.save(cliente);
    }
    
    public List<Cliente> buscarTodos() {
    	return repository.findAll();
    }
}
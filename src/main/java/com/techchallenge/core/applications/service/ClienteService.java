package com.techchallenge.core.applications.service;

import com.techchallenge.core.applications.ports.ClienteRepository;
import com.techchallenge.core.domain.Cliente;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Transactional
    public Cliente salvar(Cliente cliente) {
        return repository.save(cliente);
    }
}
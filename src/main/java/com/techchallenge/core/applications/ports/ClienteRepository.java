package com.techchallenge.core.applications.ports;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techchallenge.core.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @SuppressWarnings("unchecked")
	Cliente save(Cliente cliente);
    
    List<Cliente> findAll();

    List<Cliente> findByCpfIs(Long cpf);
}
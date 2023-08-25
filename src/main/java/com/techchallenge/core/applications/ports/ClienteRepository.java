package com.techchallenge.core.applications.ports;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.techchallenge.core.domain.Cliente;

//@Repository
public interface ClienteRepository { //extends JpaRepository<Cliente, Long> {

    @SuppressWarnings("unchecked")
	Cliente save(Cliente cliente);

    List<Cliente> findByCpfIs(Long cpf);
    
    @Query("select c from Cliente c where c.cpf = :cpf or c.email = :email")
    List<Cliente> findByCpfOrEmail(Long cpf, String email);
}
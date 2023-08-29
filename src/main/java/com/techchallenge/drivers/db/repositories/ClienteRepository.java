package com.techchallenge.drivers.db.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.techchallenge.drivers.db.entities.ClienteEntity;


@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

    @SuppressWarnings("unchecked")
	ClienteEntity save(ClienteEntity cliente);

    List<ClienteEntity> findByCpfIs(Long cpf);
    
    @Query("select c from Cliente c where c.cpf = :cpf or c.email = :email")
    List<ClienteEntity> findByCpfOrEmail(Long cpf, String email);
}
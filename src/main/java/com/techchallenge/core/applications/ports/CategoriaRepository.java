package com.techchallenge.core.applications.ports;

import com.techchallenge.core.domain.Categoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Categoria save(Categoria categoria);

    @Query("select c from Categoria c where c.nome = ?1")
    public Categoria findByNome(String nome);
}

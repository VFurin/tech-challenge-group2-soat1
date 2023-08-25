package com.techchallenge.drivers.db.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.techchallenge.drivers.db.entities.ProdutoEntity;


// @Repository
public interface ProdutoRepository { //extends JpaRepository<ProdutoEntity, Long> {

    @SuppressWarnings("unchecked")
	ProdutoEntity save(ProdutoEntity produto);

    @Query("from Produto p join fetch p.categoria where p.categoria.nome = :produtoCategoria")
    List<ProdutoEntity> findByCategoriaNome(String produtoCategoria);
    
	@Query("from Produto p join fetch p.categoria")
	List<ProdutoEntity> findAll();
	
    @Query("from Produto p join fetch p.categoria where p.categoria.id = :categoriaId")
    List<ProdutoEntity> findByCategoriaId(Long categoriaId);

    void deleteById(Long produtoId);
}

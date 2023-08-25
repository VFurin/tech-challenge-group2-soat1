package com.techchallenge.core.applications.ports;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.techchallenge.core.domain.Produto;

//@Repository
public interface ProdutoRepository { //extends JpaRepository<Produto, Long> {

    @SuppressWarnings("unchecked")
	Produto save(Produto produto);

    @Query("from Produto p join fetch p.categoria where p.categoria.nome = :produtoCategoria")
    List<Produto> findByCategoriaNome(String produtoCategoria);
    
	@Query("from Produto p join fetch p.categoria")
	List<Produto> findAll();
	
    @Query("from Produto p join fetch p.categoria where p.categoria.id = :categoriaId")
    List<Produto> findByCategoriaId(Long categoriaId);

    void deleteById(Long produtoId);
}

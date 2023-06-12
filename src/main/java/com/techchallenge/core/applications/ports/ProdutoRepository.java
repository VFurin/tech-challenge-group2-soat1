package com.techchallenge.core.applications.ports;

import com.techchallenge.core.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Produto save(Produto produto);

    List<Produto> findByCategoriaNome(String produtoCategoria);

    void deleteById(Long produtoId);
}

package com.techchallenge.core.applications.ports;

import com.techchallenge.core.domain.Cliente;
import com.techchallenge.core.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}

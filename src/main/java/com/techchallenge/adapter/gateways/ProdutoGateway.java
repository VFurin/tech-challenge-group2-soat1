package com.techchallenge.adapter.gateways;

import java.util.List;

import com.techchallenge.core.domain.entities.Produto;


public interface ProdutoGateway {
	
    Produto salvar(Produto produto);
    Produto editar(Long produtoId);
    void excluir(Long produtoId);
    List<Produto> buscarTodos();
    List<Produto> buscarPorCategoria(String produtoCategoria);
    List<Produto> buscarPorCategoria(Long categoriaId);
    void atualizar(Long id, Produto produto);
}

package com.techchallenge.core.domain.usecases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techchallenge.adapter.gateways.ProdutoGateway;
import com.techchallenge.core.domain.entities.Categoria;
import com.techchallenge.core.domain.entities.Produto;

@Service
public class ProdutoUseCase {

	@Autowired
    private ProdutoGateway gateway;

	@Autowired
    private CategoriaUseCase categoriaUseCase;
    
    
    public Produto salvar(Produto produto) {
    	Long categoriaId = produto.getCategoria().getId();
    	
        Categoria categoria = categoriaUseCase.buscarPorId(categoriaId);
        produto.setCategoria(categoria);
        
        return gateway.salvar(produto);
    }

    public Produto editar(Long produtoId) {
    	return gateway.editar(produtoId);
    }

    public void excluir(Long produtoId) {
    	gateway.excluir(produtoId);
    }
    
    public List<Produto> buscarTodos() {
    	return gateway.buscarTodos();
    }

    public List<Produto> buscarPorCategoria(String produtoCategoria) {
    	return gateway.buscarPorCategoria(produtoCategoria);
    }
    
    public List<Produto> buscarPorCategoria(Long categoriaId) {
    	return gateway.buscarPorCategoria(categoriaId);
    }
    
    public void atualizar(Long id, Produto produto) {
        Categoria categoria = categoriaUseCase.buscarPorId(produto.getCategoria().getId());
        produto.setCategoria(categoria);
        
    	gateway.atualizar(id, produto);
    }
}
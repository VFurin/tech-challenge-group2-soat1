package com.techchallenge.adapter.gateways.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import com.techchallenge.adapter.gateways.ProdutoGateway;
import com.techchallenge.core.domain.entities.Produto;
import com.techchallenge.core.domain.exception.EntidadeEmUsoException;
import com.techchallenge.drivers.db.repositories.CategoriaRepository;
import com.techchallenge.drivers.db.repositories.ProdutoRepository;

@Component
public class ProdutoGatewayImpl implements ProdutoGateway {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private CategoriaRepository categoriaRepository;
    
    private static final String MSG_PRODUTO_EM_USO = "Produto em uso com o id %d";
    private static final String MSG_PRODUTO_NAO_EXISTE = "Não existe um cadastro de produto com código %d";
    private static final String MSG_CATEGORIA_NAO_EXISTE = "Não existe uma categoria com código %d";

    @Transactional
    public Produto salvar(Produto produto) {
//    	Long categoriaId = produto.getCategoria().getId();
//    	
//        Categoria categoria = categoriaRepository.findById(produto.getCategoria().getId())
//        		.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(MSG_CATEGORIA_NAO_EXISTE, categoriaId)));
//        
//        Categoria detached = new Categoria();
//        detached.setId(categoria.getId());
//        detached.setNome(categoria.getNome());
//        
//        produto.setCategoria(detached);
//        return repository.save(produto);
    	return null;
    }

    public Produto editar(Long produtoId) {
//        return repository.findById(produtoId)
//                .orElseThrow(() -> new EntidadeNaoEncontradaException(
//                        String.format(MSG_PRODUTO_NAO_EXISTE, produtoId)));
    	return null;
    }

    @Transactional
    public void excluir(Long produtoId) {
    	try {
    		repository.deleteById(produtoId);
    		repository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_PRODUTO_EM_USO, produtoId));
		}
    }
    
    public List<Produto> buscarTodos() {
//    	return repository.findAll();
    	return null;
    }

    public List<Produto> buscarPorCategoria(String produtoCategoria) {
//        return repository.findByCategoriaNome(produtoCategoria);
    	return null;
    }
    
    public List<Produto> buscarPorCategoria(Long categoriaId) {
//    	return repository.findByCategoriaId(categoriaId);
    	return null;
    }
    
    @Transactional
    public void atualizar(Long id, Produto produto) {
//        Produto produtoEntity = repository.findById(id)
//                .orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(MSG_PRODUTO_NAO_EXISTE, id)));
//        
//    	Long categoriaId = produto.getCategoria().getId();
//    	
//        Categoria categoria = categoriaRepository.findById(produto.getCategoria().getId())
//        		.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(MSG_CATEGORIA_NAO_EXISTE, categoriaId)));
//        
//        produto.setCategoria(categoria);
//        produtoEntity.setDescricao(produto.getDescricao());
//        produtoEntity.setImagem(produto.getImagem());
//        produtoEntity.setNome(produto.getNome());
//        produtoEntity.setPreco(produto.getPreco());
    }
}

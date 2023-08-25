package com.techchallenge.core.applications.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.techchallenge.core.applications.ports.ProdutoRepository;
import com.techchallenge.core.domain.Produto;
import com.techchallenge.core.domain.exception.EntidadeEmUsoException;
import com.techchallenge.core.domain.exception.EntidadeNaoEncontradaException;
import com.techchallenge.drivers.db.entities.CategoriaEntity;
import com.techchallenge.drivers.db.repositories.CategoriaRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private CategoriaRepository categoriaRepository;
    
    private static final String MSG_PRODUTO_EM_USO = "Produto em uso com o id %d";
    private static final String MSG_PRODUTO_NAO_EXISTE = "Não existe um cadastro de produto com código %d";
    private static final String MSG_CATEGORIA_NAO_EXISTE = "Não existe uma categoria com código %d";

    @Transactional
    public Produto salvar(Produto produto) {
    	Long categoriaId = produto.getCategoria().getId();
    	
        CategoriaEntity categoria = categoriaRepository.findById(produto.getCategoria().getId())
        		.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(MSG_CATEGORIA_NAO_EXISTE, categoriaId)));
        
        CategoriaEntity detached = new CategoriaEntity();
        detached.setId(categoria.getId());
        detached.setNome(categoria.getNome());
        
        produto.setCategoria(detached);
        return repository.save(produto);
    }

    public Produto editar(Long produtoId) {
        return repository.findById(produtoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format(MSG_PRODUTO_NAO_EXISTE, produtoId)));
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
    
    public List<Produto> buscarTodos() {return repository.findAll();}

    public List<Produto> buscarPorCategoria(String produtoCategoria) {
        return repository.findByCategoriaNome(produtoCategoria);
    }
    
    public List<Produto> buscarPorCategoria(Long categoriaId) {
    	return repository.findByCategoriaId(categoriaId);
    }
    
    @Transactional
    public void atualizar(Long id, Produto produto) {
        Produto produtoEntity = repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(MSG_PRODUTO_NAO_EXISTE, id)));
        
    	Long categoriaId = produto.getCategoria().getId();
    	
        CategoriaEntity categoria = categoriaRepository.findById(produto.getCategoria().getId())
        		.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(MSG_CATEGORIA_NAO_EXISTE, categoriaId)));
        
        produto.setCategoria(categoria);
        produtoEntity.setDescricao(produto.getDescricao());
        produtoEntity.setImagem(produto.getImagem());
        produtoEntity.setNome(produto.getNome());
        produtoEntity.setPreco(produto.getPreco());
    }
}

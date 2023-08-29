package com.techchallenge.adapter.gateways.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import com.techchallenge.adapter.gateways.ProdutoGateway;
import com.techchallenge.adapter.mapper.business.ProdutoBusinessMapper;
import com.techchallenge.adapter.mapper.db.ProdutoEntityMapper;
import com.techchallenge.core.domain.entities.Produto;
import com.techchallenge.core.domain.exception.EntidadeEmUsoException;
import com.techchallenge.core.domain.exception.EntidadeNaoEncontradaException;
import com.techchallenge.drivers.db.entities.ProdutoEntity;
import com.techchallenge.drivers.db.repositories.ProdutoRepository;

@Component
public class ProdutoGatewayImpl implements ProdutoGateway {

    @Autowired
    private ProdutoRepository repository;
    @Autowired
    private ProdutoEntityMapper mapper;
    @Autowired
    private ProdutoBusinessMapper businessMapper;
    
    
    private static final String MSG_PRODUTO_EM_USO = "Produto em uso com o id %d";
    private static final String MSG_PRODUTO_NAO_EXISTE = "Não existe um cadastro de produto com código %d";
    private static final String MSG_PRODUTO_NAO_ENCONTRADO = "Produto com o id %d não encontrado";

    @Transactional
    public Produto salvar(Produto produto) {
    	ProdutoEntity entity = repository.save(mapper.toModel(produto)); 
        return businessMapper.toModel(repository.save(entity));
    }

    public Produto editar(Long produtoId) {
        ProdutoEntity entity = repository.findById(produtoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(MSG_PRODUTO_NAO_EXISTE, produtoId)));
        
        return businessMapper.toModel(entity);
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
    	List<ProdutoEntity> entities = repository.findAll();
    	return businessMapper.toCollectionModel(entities);
    }

    public List<Produto> buscarPorCategoria(String produtoCategoria) {
    	List<ProdutoEntity> entities = repository.findByCategoriaNome(produtoCategoria);
    	return businessMapper.toCollectionModel(entities);
    }
    
    public List<Produto> buscarPorCategoria(Long categoriaId) {
    	List<ProdutoEntity> entities = repository.findByCategoriaId(categoriaId);
    	return businessMapper.toCollectionModel(entities);
    }
    
    @Transactional
    public void atualizar(Long id, Produto produto) {
        ProdutoEntity entity = repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(MSG_PRODUTO_NAO_EXISTE, id)));
        
        entity.setDescricao(produto.getDescricao());
        entity.setImagem(produto.getImagem());
        entity.setNome(produto.getNome());
        entity.setPreco(produto.getPreco());
    }

	@Override
	public Produto buscarPorId(Long produtoId) {
		ProdutoEntity entity = repository.findById(produtoId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(MSG_PRODUTO_NAO_ENCONTRADO, produtoId)));
		
		return businessMapper.toModel(entity);
	}
}

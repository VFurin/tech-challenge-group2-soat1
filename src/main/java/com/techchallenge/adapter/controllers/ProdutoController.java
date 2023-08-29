package com.techchallenge.adapter.controllers;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.techchallenge.adapter.driver.model.ProdutoModel;
import com.techchallenge.adapter.driver.model.input.ProdutoInput;
import com.techchallenge.adapter.mapper.api.ProdutoApiMapper;
import com.techchallenge.core.domain.entities.Produto;
import com.techchallenge.core.domain.usecases.ProdutoUseCase;

@Component
public class ProdutoController {

	@Autowired
	private ProdutoUseCase useCase;
	
	@Autowired
	private ProdutoApiMapper mapper;

    public Collection<ProdutoModel> listar() {
        List<Produto> todosProdutos = useCase.buscarTodos();
        return mapper.toCollectionModel(todosProdutos);
    }

    public Collection<ProdutoModel> listarPorCategoria(@PathVariable String categoriaNome) {
        List<Produto> produto = useCase.buscarPorCategoria(categoriaNome);
        return mapper.toCollectionModel(produto);
    }
    
    public Collection<ProdutoModel> listarPorCategoria(@PathVariable Long categoriaId) {
        List<Produto> produto = useCase.buscarPorCategoria(categoriaId);
        return mapper.toCollectionModel(produto);
    }
    
	public ProdutoModel adicionar(@RequestBody @Valid ProdutoInput input) {
		Produto produto = mapper.toDomainObject(input);
		produto = useCase.salvar(produto);

		return mapper.toModel(produto);
	}
	
	public void remover(@PathVariable Long produtoId) {
		this.useCase.excluir(produtoId);
	}
	
	public void atualizar(@PathVariable Long produtoId, @RequestBody @Valid ProdutoInput input) {
		Produto produto = mapper.toDomainObject(input);
		useCase.atualizar(produtoId, produto);
	}
}

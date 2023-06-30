package com.techchallenge.adapter.driver;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techchallenge.adapter.driver.model.ProdutoModel;
import com.techchallenge.adapter.driver.model.input.ProdutoInput;
import com.techchallenge.adapter.mapper.ProdutoMapper;
import com.techchallenge.core.applications.service.ProdutoService;
import com.techchallenge.core.domain.Produto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Produtos")
@RestController
@RequestMapping(value = "/produtos", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @Autowired
    private ProdutoMapper mapper;

    @GetMapping
    public Collection<ProdutoModel> listar() {
        List<Produto> todosProdutos = service.buscarTodos();
        return mapper.toCollectionModel(todosProdutos);
    }

    @GetMapping(value="/{categoria}")
    public Collection<ProdutoModel> listarPorCategoria(@PathVariable String categoria) {
        List<Produto> produto = service.buscarPorCategoria(categoria);
        return mapper.toCollectionModel(produto);
    }
    
	@ApiOperation("Inclui um produto na plataforma")
	@ApiResponses({ 
			@ApiResponse(code = 201, message = "Produto incluso com sucesso") 
			})
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoModel adicionar(@RequestBody @Valid ProdutoInput input) {
		Produto produto = mapper.toDomainObject(input);
		produto = service.salvar(produto);

		return mapper.toModel(produto);
	}
	
	@ApiOperation("Exclui um produto na plataforma")
	@ApiResponses({ 
			@ApiResponse(code = 204, message = "Produto excluído com sucesso") 
			})
	@DeleteMapping(value="/{produtoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long produtoId) {
		this.service.excluir(produtoId);
	}
	
	@ApiOperation("Atualiza um produto na plataforma")
	@ApiResponses({ 
			@ApiResponse(code = 204, message = "Produto atualizado com sucesso") 
			})
	@PutMapping(value="/{produtoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizar(@PathVariable Long produtoId, @RequestBody @Valid ProdutoInput input) {
		Produto produto = mapper.toDomainObject(input);
		service.atualizar(produtoId, produto);
	}
}

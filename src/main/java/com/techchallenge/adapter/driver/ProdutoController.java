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

import com.techchallenge.adapter.driver.exceptionhandler.Problem;
import com.techchallenge.adapter.driver.model.ProdutoModel;
import com.techchallenge.adapter.driver.model.input.ProdutoInput;
import com.techchallenge.adapter.mapper.ProdutoMapper;
import com.techchallenge.core.applications.service.ProdutoService;
import com.techchallenge.core.domain.Produto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

//@Api(tags = "Produtos")
//@RestController
//@RequestMapping(value = "/produtos", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @Autowired
    private ProdutoMapper mapper;

	@ApiOperation("Listar produtos cadastrados na plataforma")
	@ApiResponses({ 
			@ApiResponse(code = 200, message = "Lista de produtos com sucesso") 
			})
    @GetMapping
    public Collection<ProdutoModel> listar() {
//        List<Produto> todosProdutos = service.buscarTodos();
//        return mapper.toCollectionModel(todosProdutos);
		return null;
    }

	@ApiOperation("Listar produtos filtrando por nome de categoria")
	@ApiResponses({ 
			@ApiResponse(code = 200, message = "Lista de produtos por nome de categoria com sucesso") 
			})
    // Evitar ambiguidade do resource path
    @GetMapping(value="/categorias/nome/{categoriaNome}")
    public Collection<ProdutoModel> listarPorCategoria(@PathVariable String categoriaNome) {
//        List<Produto> produto = service.buscarPorCategoria(categoriaNome);
//        return mapper.toCollectionModel(produto);
		return null;
    }
    
	@ApiOperation("Listar produtos filtrando por código de categoria")
	@ApiResponses({ 
			@ApiResponse(code = 200, message = "Lista de produtos por códgo de categoria com sucesso") 
			})
    // Evitar ambiguidade do resource path
    @GetMapping(value="/categorias/codigo/{categoriaId}")
    public Collection<ProdutoModel> listarPorCategoria(@PathVariable Long categoriaId) {
//        List<Produto> produto = service.buscarPorCategoria(categoriaId);
//        return mapper.toCollectionModel(produto);
		return null;
    }
    
	@ApiOperation("Inclui um produto na plataforma")
	@ApiResponses({ 
			@ApiResponse(code = 201, message = "Produto incluso com sucesso"),
            @ApiResponse(code = 404, message = "Categoria informada não encontrada", response = Problem.class)
			})
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoModel adicionar(@RequestBody @Valid ProdutoInput input) {
//		Produto produto = mapper.toDomainObject(input);
//		produto = service.salvar(produto);
//
//		return mapper.toModel(produto);
		return null;
	}
	
	@ApiOperation("Exclui um produto na plataforma")
	@ApiResponses({ 
			@ApiResponse(code = 204, message = "Produto excluído com sucesso"),
			@ApiResponse(code = 400, message = "Produto em uso", response = Problem.class),
			@ApiResponse(code = 404, message = "Produto não encontrado", response = Problem.class)
			})
	@DeleteMapping(value="/{produtoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long produtoId) {
		this.service.excluir(produtoId);
	}
	
	@ApiOperation("Atualiza um produto na plataforma")
	@ApiResponses({ 
			@ApiResponse(code = 204, message = "Produto atualizado com sucesso"),
			@ApiResponse(code = 404, message = "Produto não encontrado", response = Problem.class)
			})
	@PutMapping(value="/{produtoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizar(@PathVariable Long produtoId, @RequestBody @Valid ProdutoInput input) {
		Produto produto = mapper.toDomainObject(input);
//		service.atualizar(produtoId, produto);
	}
}

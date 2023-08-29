package com.techchallenge.drivers.apis;

import java.util.Collection;

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

import com.techchallenge.adapter.controllers.ProdutoController;
import com.techchallenge.adapter.driver.exceptionhandler.Problem;
import com.techchallenge.adapter.driver.model.ProdutoModel;
import com.techchallenge.adapter.driver.model.input.ProdutoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Produtos")
@RestController
@RequestMapping(value = "/produtos", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProdutoRestController {

    @Autowired
    private ProdutoController controller;

	@ApiOperation("Listar produtos cadastrados na plataforma")
	@ApiResponses({ 
			@ApiResponse(code = 200, message = "Lista de produtos com sucesso") 
			})
    @GetMapping
    public Collection<ProdutoModel> listar() {
        return controller.listar();
    }

	@ApiOperation("Listar produtos filtrando por nome de categoria")
	@ApiResponses({ 
			@ApiResponse(code = 200, message = "Lista de produtos por nome de categoria com sucesso") 
			})
    // Evitar ambiguidade do resource path
    @GetMapping(value="/categorias/nome/{categoriaNome}")
    public Collection<ProdutoModel> listarPorCategoria(@PathVariable String categoriaNome) {
        return controller.listarPorCategoria(categoriaNome);
    }
    
	@ApiOperation("Listar produtos filtrando por código de categoria")
	@ApiResponses({ 
			@ApiResponse(code = 200, message = "Lista de produtos por códgo de categoria com sucesso") 
			})
    // Evitar ambiguidade do resource path
    @GetMapping(value="/categorias/codigo/{categoriaId}")
    public Collection<ProdutoModel> listarPorCategoria(@PathVariable Long categoriaId) {
        return controller.listarPorCategoria(categoriaId);
    }
    
	@ApiOperation("Inclui um produto na plataforma")
	@ApiResponses({ 
			@ApiResponse(code = 201, message = "Produto incluso com sucesso"),
            @ApiResponse(code = 404, message = "Categoria informada não encontrada", response = Problem.class)
			})
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoModel adicionar(@RequestBody @Valid ProdutoInput input) {
		return controller.adicionar(input);
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
		this.controller.remover(produtoId);
	}
	
	@ApiOperation("Atualiza um produto na plataforma")
	@ApiResponses({ 
			@ApiResponse(code = 204, message = "Produto atualizado com sucesso"),
			@ApiResponse(code = 404, message = "Produto não encontrado", response = Problem.class)
			})
	@PutMapping(value="/{produtoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizar(@PathVariable Long produtoId, @RequestBody @Valid ProdutoInput input) {
		controller.atualizar(produtoId, input);
	}
}

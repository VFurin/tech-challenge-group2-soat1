package com.techchallenge.adapter.driver;

import com.techchallenge.adapter.driver.model.ProdutoModel;
import com.techchallenge.adapter.driver.model.input.ProdutoInput;
import com.techchallenge.adapter.mapper.ProdutoMapper;
import com.techchallenge.core.applications.service.ProdutoService;
import com.techchallenge.core.domain.Produto;

import io.swagger.annotations.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@Api(tags = "Produtos")
@RestController
@RequestMapping(value = "/produtos", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @Autowired
    private ProdutoMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoModel criar(@RequestBody @Valid ProdutoInput input) {
        Produto produto = mapper.toDomainObject(input);
        produto = service.salvar(produto);

        return mapper.toModel(produto);
    }
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

    @PutMapping(value= "/{produtoId}")
    public ProdutoModel atualizar(@PathVariable Long produtoId, @RequestBody @Valid ProdutoInput input) {
        Produto produtoAtual = service.editar(produtoId);
        mapper.copyToDomainObject(input, produtoAtual);
        produtoAtual = service.salvar(produtoAtual);

        return mapper.toModel(produtoAtual);
    }

    @DeleteMapping(value= "/{produtoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long produtoId) {
        service.excluir(produtoId);
    }
}

package com.techchallenge.adapter.driver;

import com.techchallenge.adapter.driver.model.ProdutoModel;
import com.techchallenge.adapter.mapper.ProdutoMapper;
import com.techchallenge.core.applications.service.ProdutoService;
import com.techchallenge.core.domain.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

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
}

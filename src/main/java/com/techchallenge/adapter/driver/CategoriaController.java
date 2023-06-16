package com.techchallenge.adapter.driver;

import com.techchallenge.adapter.driver.model.CategoriaModel;
import com.techchallenge.adapter.mapper.CategoriaMapper;
import com.techchallenge.core.applications.service.CategoriaService;
import com.techchallenge.core.domain.Categoria;

import io.swagger.annotations.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

feature/ajustes-model-swagger
@Api(tags = "Categorias")
  
import java.util.Collection;
import java.util.List;
main
@RestController
@RequestMapping(value = "/categorias", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @Autowired
    private CategoriaMapper mapper;

    @GetMapping
    public Collection<CategoriaModel> listar() {
        List<Categoria> todasCategorias = service.buscarTodos();
        return mapper.toCollectionModel(todasCategorias);
    }
}

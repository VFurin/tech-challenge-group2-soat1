package com.techchallenge.drivers.apis;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techchallenge.adapter.controllers.CategoriaController;
import com.techchallenge.adapter.driver.model.CategoriaModel;

import io.swagger.annotations.Api;

@Api(tags = "Categorias")
@RestController
@RequestMapping(value = "/categorias", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoriaRestController {

    @Autowired
    private CategoriaController controller;

    @GetMapping
    public Collection<CategoriaModel> listar() {
        Collection<CategoriaModel> todasCategorias = controller.listar();
        
        return todasCategorias;
    }
}

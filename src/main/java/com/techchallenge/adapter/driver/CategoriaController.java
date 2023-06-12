package com.techchallenge.adapter.driver;

import com.techchallenge.adapter.driver.model.CategoriaModel;
import com.techchallenge.adapter.driver.model.input.CategoriaInput;
import com.techchallenge.adapter.mapper.CategoriaMapper;
import com.techchallenge.core.applications.service.CategoriaService;
import com.techchallenge.core.domain.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/categorias", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @Autowired
    private CategoriaMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoriaModel criar(@RequestBody @Valid CategoriaInput input) {
        Categoria categoria = mapper.toDomainObject(input);
        categoria = service.salvar(categoria);

        return mapper.toModel(categoria);
    }

}

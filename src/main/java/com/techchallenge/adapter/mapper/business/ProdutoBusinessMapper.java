package com.techchallenge.adapter.mapper.business;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techchallenge.core.domain.entities.Produto;
import com.techchallenge.drivers.db.entities.ProdutoEntity;

@Component
public class ProdutoBusinessMapper {

    @Autowired
    private ModelMapper mapper;

    public Produto toModel(ProdutoEntity produto) {
        return mapper.map(produto, Produto.class);
    }

    public List<Produto> toCollectionModel(Collection<ProdutoEntity> produtos) {
        return produtos.stream()
                .map(c -> mapper.map(c, Produto.class))
                .collect(Collectors.toList());
    }
}

package com.techchallenge.adapter.mapper;

import com.techchallenge.core.domain.Produto;
import org.modelmapper.ModelMapper;
import com.techchallenge.adapter.driver.model.ProdutoModel;
import com.techchallenge.adapter.driver.model.input.ProdutoInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class ProdutoMapper {

    @Autowired
    private ModelMapper mapper;

    public Produto toDomainObject(ProdutoInput input) {
        return mapper.map(input, Produto.class);
    }

    public ProdutoModel toModel(Produto produto) {
        return mapper.map(produto, ProdutoModel.class);
    }

    public Collection<ProdutoModel> toCollectionModel(Collection<Produto> produtos) {
        return produtos.stream()
                .map(c -> mapper.map(c, ProdutoModel.class))
                .collect(Collectors.toList());
    }

    public void copyToDomainObject(ProdutoInput input, Produto produtoAtual) {
        mapper.map(input, produtoAtual);
    }
}

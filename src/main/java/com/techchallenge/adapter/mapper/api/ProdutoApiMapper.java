package com.techchallenge.adapter.mapper.api;

import java.util.Collection;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techchallenge.adapter.driver.model.ProdutoModel;
import com.techchallenge.adapter.driver.model.input.ProdutoInput;
import com.techchallenge.core.domain.entities.Categoria;
import com.techchallenge.core.domain.entities.Produto;

@Component
public class ProdutoApiMapper {

    @Autowired
    private ModelMapper mapper;

    public Produto toDomainObject(ProdutoInput input) {
        Produto produto = mapper.map(input, Produto.class);
        
        // Não sei por qual motivo, mapper está associando o atributo categoriaId
        // para o id do produto. Por isso eu forcei o null.
        produto.setId(null);
        produto.setCategoria(new Categoria());
        produto.getCategoria().setId(input.getCategoriaId());
        
        return produto;
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

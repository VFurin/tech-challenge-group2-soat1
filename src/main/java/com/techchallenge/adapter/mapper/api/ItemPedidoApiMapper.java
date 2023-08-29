package com.techchallenge.adapter.mapper.api;

import java.util.Collection;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techchallenge.adapter.driver.model.ItemPedidoModel;
import com.techchallenge.adapter.driver.model.input.ItemPedidoInput;
import com.techchallenge.core.domain.entities.ItemPedido;
import com.techchallenge.core.domain.entities.Produto;

@Component
public class ItemPedidoApiMapper {

    @Autowired
    private ModelMapper mapper;

    public ItemPedido toDomainObject(ItemPedidoInput input) {
        ItemPedido itemPedido = mapper.map(input, ItemPedido.class);
        
        itemPedido.setProduto(new Produto());
        itemPedido.getProduto().setId(input.getProdutoId());
        
        return itemPedido;
    }

    public ItemPedidoModel toModel(ItemPedido itemPedido) {
        return mapper.map(itemPedido, ItemPedidoModel.class);
    }

    public Collection<ItemPedidoModel> toCollectionModel(Collection<ItemPedido> itens) {
        return itens.stream()
                .map(c -> mapper.map(c, ItemPedidoModel.class))
                .collect(Collectors.toList());
    }
}
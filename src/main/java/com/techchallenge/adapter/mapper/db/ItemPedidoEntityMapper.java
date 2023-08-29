package com.techchallenge.adapter.mapper.db;

import java.util.Collection;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techchallenge.core.domain.entities.ItemPedido;
import com.techchallenge.drivers.db.entities.ItemPedidoEntity;

@Component
public class ItemPedidoEntityMapper {

	@Autowired
	private ModelMapper mapper;
	
	public ItemPedidoEntity toModel(ItemPedido itemPedido) {
		return mapper.map(itemPedido, ItemPedidoEntity.class);
	}
	
	public Collection<ItemPedidoEntity> toCollectionModel(Collection<ItemPedido> itens) {
		return itens.stream()
				.map(c -> mapper.map(c, ItemPedidoEntity.class))
				.collect(Collectors.toList());
	}
}

package com.techchallenge.adapter.mapper.business;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techchallenge.core.domain.entities.ItemPedido;
import com.techchallenge.drivers.db.entities.ItemPedidoEntity;

@Component
public class ItemPedidoBusinessMapper {

	@Autowired
	private ModelMapper mapper;
	
	public ItemPedido toModel(ItemPedidoEntity itemPedido) {
		return mapper.map(itemPedido, ItemPedido.class);
	}
	
	public List<ItemPedido> toCollectionModel(Collection<ItemPedidoEntity> itens) {
		return itens.stream()
				.map(c -> mapper.map(c, ItemPedido.class))
				.collect(Collectors.toList());
	}
}

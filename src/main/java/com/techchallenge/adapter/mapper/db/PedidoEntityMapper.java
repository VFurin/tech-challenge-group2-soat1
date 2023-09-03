package com.techchallenge.adapter.mapper.db;

import java.util.Collection;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techchallenge.core.domain.entities.Pedido;
import com.techchallenge.drivers.db.entities.PedidoEntity;

@Component
public class PedidoEntityMapper {

	@Autowired
	private ModelMapper mapper;
	
	public PedidoEntity toModel(Pedido pedido) {
		return mapper.map(pedido, PedidoEntity.class);
	}
	
	public PedidoEntity toModel(PedidoEntity pedido) {
		return mapper.map(pedido, PedidoEntity.class);
	}
	
	public void toModel(Pedido pedido, PedidoEntity entity) {
		mapper.map(pedido, entity);
	}
	
	public Collection<PedidoEntity> toCollectionModel(Collection<Pedido> pedidos) {
		return pedidos.stream()
				.map(c -> mapper.map(c, PedidoEntity.class))
				.collect(Collectors.toList());
	}
}

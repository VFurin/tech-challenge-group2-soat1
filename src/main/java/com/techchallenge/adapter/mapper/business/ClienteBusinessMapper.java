package com.techchallenge.adapter.mapper.business;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techchallenge.core.domain.entities.Cliente;
import com.techchallenge.drivers.db.entities.ClienteEntity;

@Component
public class ClienteBusinessMapper {

	@Autowired
	private ModelMapper mapper;
	
	public Cliente toModel(ClienteEntity cliente) {
		return mapper.map(cliente, Cliente.class);
	}
	
	public List<Cliente> toCollectionModel(Collection<ClienteEntity> clientes) {
		return clientes.stream()
				.map(c -> mapper.map(c, Cliente.class))
				.collect(Collectors.toList());
	}
}

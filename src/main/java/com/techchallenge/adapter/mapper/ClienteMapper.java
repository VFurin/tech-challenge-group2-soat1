package com.techchallenge.adapter.mapper;

import java.util.Collection;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techchallenge.adapter.driver.model.ClienteModel;
import com.techchallenge.adapter.driver.model.input.ClienteAtualizacaoInput;
import com.techchallenge.adapter.driver.model.input.ClienteInput;
import com.techchallenge.core.domain.Cliente;

@Component
public class ClienteMapper {

	@Autowired
	private ModelMapper mapper;
	
	public Cliente toDomainObject(ClienteInput input) {
		return mapper.map(input, Cliente.class);
	}
	
	public Cliente toDomainObject(ClienteAtualizacaoInput input) {
		return mapper.map(input, Cliente.class);
	}
	
	public ClienteModel toModel(Cliente cliente) {
		return mapper.map(cliente, ClienteModel.class);
	}
	
	public Collection<ClienteModel> toCollectionModel(Collection<Cliente> clientes) {
		return clientes.stream()
				.map(c -> mapper.map(c, ClienteModel.class))
				.collect(Collectors.toList());
	}
}

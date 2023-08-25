package com.techchallenge.adapter.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techchallenge.adapter.driver.model.ClienteModel;
import com.techchallenge.adapter.driver.model.input.ClienteAtualizacaoInput;
import com.techchallenge.adapter.driver.model.input.ClienteInput;
import com.techchallenge.adapter.mapper.api.ClienteApiMapper;
import com.techchallenge.core.domain.entities.Cliente;
import com.techchallenge.core.domain.usecases.ClienteUseCase;

@Component
public class ClienteController {
	
	@Autowired
	private ClienteUseCase useCase;
	
	@Autowired
	private ClienteApiMapper mapper;
	
	public ClienteModel adicionar(ClienteInput input) {
		Cliente cliente = mapper.toDomainObject(input);
		cliente = useCase.salvar(cliente);
		
		return mapper.toModel(cliente);
	}

	public ClienteModel buscarPorCpf(Long cpf) {
		List<Cliente> clientes = useCase.buscarPorCpf(cpf);

		return mapper.toCollectionModel(clientes)
				.stream()
				.findFirst()
				.orElse(null);
	}
	
	public void remover(Long clienteId) {
		this.useCase.excluir(clienteId);
	}
	
    public void atualizarDadosCliente(Long id, ClienteAtualizacaoInput clienteInput) {
    	Cliente cliente = mapper.toDomainObject(clienteInput);
        useCase.atualizarDadosCliente(id, cliente);
    }
}
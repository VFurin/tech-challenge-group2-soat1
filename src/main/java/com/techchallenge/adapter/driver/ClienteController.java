package com.techchallenge.adapter.driver;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techchallenge.adapter.driver.model.ClienteModel;
import com.techchallenge.adapter.driver.model.input.ClienteInput;
import com.techchallenge.adapter.mapper.ClienteMapper;
import com.techchallenge.core.applications.service.ClienteService;
import com.techchallenge.core.domain.Cliente;

@RestController
@RequestMapping(value = "/clientes", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClienteController {
	
	@Autowired
	private ClienteService service;
	
	@Autowired
	private ClienteMapper mapper;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteModel adicionar(@RequestBody @Valid ClienteInput input) {
		Cliente cliente = mapper.toDomainObject(input);
		cliente = service.salvar(cliente);
		
		return mapper.toModel(cliente);
	}
	
	@GetMapping
	public Collection<ClienteModel> listar() {
		List<Cliente> todosClientes = service.buscarTodos();
		return mapper.toCollectionModel(todosClientes);
	}
}

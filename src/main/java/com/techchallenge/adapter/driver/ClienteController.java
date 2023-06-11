package com.techchallenge.adapter.driver;

import com.techchallenge.adapter.driver.model.ClienteModel;
import com.techchallenge.adapter.driver.model.input.ClienteInput;
import com.techchallenge.adapter.mapper.ClienteMapper;
import com.techchallenge.core.applications.service.ClienteService;
import com.techchallenge.core.domain.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

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

	@GetMapping(value = "/{cpf}")
	public ClienteModel buscarPorCpf(@PathVariable Long cpf) {
		List<Cliente> clientes = service.buscarPorCpf(cpf);

		return mapper.toCollectionModel(clientes)
				.stream()
				.findFirst()
				.orElse(null);
	}
}

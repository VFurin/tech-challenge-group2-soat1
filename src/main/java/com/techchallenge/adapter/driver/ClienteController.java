package com.techchallenge.adapter.driver;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techchallenge.adapter.driver.exceptionhandler.Problem;
import com.techchallenge.adapter.driver.model.ClienteModel;
import com.techchallenge.adapter.driver.model.input.ClienteAtualizacaoInput;
import com.techchallenge.adapter.driver.model.input.ClienteInput;
import com.techchallenge.adapter.mapper.ClienteMapper;
import com.techchallenge.core.applications.service.ClienteService;
import com.techchallenge.core.domain.Cliente;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Clientes")
@RestController
@RequestMapping(value = "/clientes", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClienteController {
	
	@Autowired
	private ClienteService service;
	
	@Autowired
	private ClienteMapper mapper;
	
	@ApiOperation("Inclui um cliente a plataforma")
	@ApiResponses({ 
			@ApiResponse(code = 201, message = "Cliente incluso com sucesso"),
			@ApiResponse(code = 400, message = "Cliente com CPF ou e-mail já cadastrado")
			})
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteModel adicionar(@RequestBody @Valid ClienteInput input) {
		Cliente cliente = mapper.toDomainObject(input);
		cliente = service.salvar(cliente);

		return mapper.toModel(cliente);
	}

	@ApiOperation("Consultar cliente por CPF")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "Cliente referente ao CPF informado"),
		@ApiResponse(code = 404, message = "Cliente não encontrado com o CPF informado", response = Problem.class) 
	})
	@GetMapping(value = "/{cpf}")
	public ClienteModel buscarPorCpf(@ApiParam(value = "CPF para consulta (apenas números)", example = "12345678901") @PathVariable Long cpf) {
		List<Cliente> clientes = service.buscarPorCpf(cpf);

		return mapper.toCollectionModel(clientes)
				.stream()
				.findFirst()
				.orElse(null);
	}
	
	@ApiOperation("Exclui um cliente na plataforma")
	@ApiResponses({ 
			@ApiResponse(code = 204, message = "Cliente excluído com sucesso"),
			@ApiResponse(code = 400, message = "Cliente com o ID informado não pode ser excluído", response = Problem.class),
			@ApiResponse(code = 404, message = "Cliente com o ID informado não encontrado", response = Problem.class) 
			})
	@DeleteMapping(value="/{clienteId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long clienteId) {
		this.service.excluir(clienteId);
	}
	
    @ApiOperation("Atualiza dados do cliente")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Dados do cliente atualizado"),
            @ApiResponse(code = 400, message = "Dados inconsistentes", response = Problem.class),
            @ApiResponse(code = 404, message = "Cliente com o ID informado não encontrado", response = Problem.class)
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(value = "/{id}")
    public void atualizarDadosCliente(@ApiParam(value = "ID do cliente", example = "12345678") @PathVariable Long id, @Valid @RequestBody ClienteAtualizacaoInput clienteInput) {
    	Cliente cliente = mapper.toDomainObject(clienteInput);
        service.atualizarDadosCliente(id, cliente);
    }
}

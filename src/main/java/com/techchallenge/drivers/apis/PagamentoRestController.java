package com.techchallenge.drivers.apis;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techchallenge.adapter.controllers.PagamentoController;
import com.techchallenge.adapter.driver.model.TipoPagamentoModel;
import com.techchallenge.adapter.driver.model.input.TipoPagamentoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Pagamentos")
@RestController
@RequestMapping(value = "/pagamentos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PagamentoRestController {
	
    @Autowired
    private PagamentoController controller;
    
	@ApiOperation("Efetuar pagamento do pedido na plataforma")
	@ApiResponses({ 
			@ApiResponse(code = 201, message = "Pagamento registrado com sucesso"),
			@ApiResponse(code = 404, message = "Caso o pedido ou pagamento com o ID informado não exista")
			})
	@PutMapping("/pedidos/{pedidoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void realizarPagamento(@PathVariable Long pedidoId, @RequestBody TipoPagamentoInput tipoPagamentoInput) {
		controller.realizarPagamento(pedidoId, tipoPagamentoInput);

	}
	
	@ApiOperation("Consultar tipos de pagamentos aceitos na plataforma")
	@ApiResponses({ 
			@ApiResponse(code = 201, message = "Consulta efetuada com sucesso"),
			@ApiResponse(code = 404, message = "Caso não exista registros na plataforma")
			})
	@GetMapping("/tipos-pagamento")
	@ResponseStatus(HttpStatus.OK)
	public Collection<TipoPagamentoModel> listar() {
		return controller.listar();
	}
}
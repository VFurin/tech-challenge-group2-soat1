package com.techchallenge.adapter.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.techchallenge.adapter.driver.model.input.PedidoInput;
import com.techchallenge.adapter.mapper.api.PedidoApiMapper;
import com.techchallenge.core.domain.entities.Pedido;
import com.techchallenge.core.domain.usecases.FakeCheckoutUseCase;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
public class FakeCheckoutController {
	
    @Autowired
    private FakeCheckoutUseCase useCase;
    
    @Autowired
    private PedidoApiMapper pedidoMapper;
	
	@ApiOperation("Efetuar checkout do pedido na plataforma")
	@ApiResponses({ 
			@ApiResponse(code = 200, message = "Checkout realizado com sucesso"),
			@ApiResponse(code = 400, message = "Caso o pedido não tenha itens associados"),
			@ApiResponse(code = 404, message = "Caso o pedido com o ID informado não exista")
			})
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public String checkout(@RequestBody PedidoInput pedidoInput) {
		
		Pedido pedido = pedidoMapper.toDomainObject(pedidoInput);
		return pedidoMapper.toModel(useCase.checkout(pedido)).getId().toString();
	}
}

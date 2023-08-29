package com.techchallenge.drivers.apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techchallenge.adapter.controllers.FakeCheckoutController;
import com.techchallenge.adapter.driver.model.input.PedidoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Checkout")
@RestController
@RequestMapping(value = "/checkout", produces = MediaType.APPLICATION_JSON_VALUE)
public class FakeCheckoutRestController {
	
    @Autowired
    private FakeCheckoutController controller;
    
	@ApiOperation("Efetuar checkout do pedido na plataforma")
	@ApiResponses({ 
			@ApiResponse(code = 200, message = "Checkout realizado com sucesso"),
			@ApiResponse(code = 400, message = "Caso o pedido não tenha itens associados"),
			@ApiResponse(code = 404, message = "Caso o pedido com o ID informado não exista")
			})
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public String checkout(@RequestBody PedidoInput pedidoInput) {
		return controller.checkout(pedidoInput);
	}
}

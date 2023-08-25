package com.techchallenge.adapter.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.techchallenge.adapter.driver.model.PedidoModel;
import com.techchallenge.adapter.driver.model.input.PedidoInput;
import com.techchallenge.adapter.mapper.PedidoMapper;
import com.techchallenge.core.applications.service.FakeCheckoutService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

//@Api(tags = "Checkout")
//@RestController
//@RequestMapping(value = "/checkout", produces = MediaType.APPLICATION_JSON_VALUE)
public class FakeCheckoutController {
	
    @Autowired
    private FakeCheckoutService service;
    
    @Autowired
    private PedidoMapper pedidoMapper;
	
	@ApiOperation("Efetuar checkout do pedido na plataforma")
	@ApiResponses({ 
			@ApiResponse(code = 200, message = "Checkout realizado com sucesso"),
			@ApiResponse(code = 400, message = "Caso o pedido não tenha itens associados"),
			@ApiResponse(code = 404, message = "Caso o pedido com o ID informado não exista")
			})
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public PedidoModel checkout(@RequestBody PedidoInput pedidoInput) {
		
//		Pedido pedido = pedidoMapper.toDomainObject(pedidoInput);
//		return pedidoMapper.toModel(service.checkout(pedido));
		return null;
	}
}

package com.techchallenge.drivers.apis;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techchallenge.adapter.controllers.PedidoController;
import com.techchallenge.adapter.driver.exceptionhandler.Problem;
import com.techchallenge.adapter.driver.model.ItemPedidoModel;
import com.techchallenge.adapter.driver.model.PedidoModel;
import com.techchallenge.adapter.driver.model.input.ItemPedidoInput;
import com.techchallenge.core.domain.entities.StatusPedido;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Pedidos")
@RestController
@RequestMapping(value = "/pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PedidoRestController {

    @Autowired
    private PedidoController controller;
    
    @ApiOperation("Consulta pedido pelo ID do pedido")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Pedido"),
            @ApiResponse(code = 404, message = "Pedido não encontrado com o ID informado", response = Problem.class)

    })
    @GetMapping(value = "/{id}")
    public PedidoModel buscarPedidosPorId(@ApiParam(value = "ID do pedido", example = "12345678") @PathVariable Long id) {
        return controller.buscarPedidosPorId(id);
    }

    @ApiOperation("Lista os pedidos, podendo filtrar pelo status")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Listagem de pedidos"),
    })
    @GetMapping
    public Collection<PedidoModel> listarPedidos(@ApiParam(value = "Status dos pedidos", example = "PREPARACAO") @RequestParam(required = false) String status) {
        if (status != null) {
            StatusPedido statusPedido = StatusPedido.valueOf(status);
            return controller.listarPedidos(statusPedido);
        } else {
            return controller.listarPedidos();
        }
    }

    @ApiOperation("Atualiza o status do pedido")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Status do pedido atualizado"),
            @ApiResponse(code = 400, message = "Status inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Pedido não encontrado com o ID informado", response = Problem.class)
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(value = "/{id}/status")
    public void atualizarStatusDoPedido(@ApiParam(value = "ID do pedido", example = "12345678") @PathVariable Long id, @RequestBody String novoStatus) {
    	controller.atualizarStatusDoPedido(id, novoStatus);
    }
    
	@ApiOperation("Adicionar itens ao pedido na plataforma")
	@ApiResponses({ 
			@ApiResponse(code = 201, message = "Itens adicionados com sucesso"),
            @ApiResponse(code = 400, message = "Produtos já adicionados com o IDs informados", response = Problem.class),
            @ApiResponse(code = 404, message = "Pedido não encontrado com o ID informado", response = Problem.class)
			})
	@PostMapping(value = "/{id}/items")
	@ResponseStatus(HttpStatus.OK)
	public ItemPedidoModel adicionarProduto(@PathVariable Long id, @RequestBody @Valid ItemPedidoInput input) {
		return this.controller.adicionarProduto(id, input);
	}
	
	@ApiOperation("Atualizar itens ao pedido na plataforma")
	@ApiResponses({ 
			@ApiResponse(code = 201, message = "Itens atualizados com sucesso"),
            @ApiResponse(code = 400, message = "Produtos não encontrados com o IDs informados", response = Problem.class),
            @ApiResponse(code = 404, message = "Pedido não encontrado com o ID informado", response = Problem.class)
			})
	@PutMapping(value = "/{id}/items")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarProduto(@PathVariable Long id, @RequestBody @Valid ItemPedidoInput input) {
		this.controller.atualizarProduto(id, input);
	}
	
	@ApiOperation("Exclui um item do pedido na plataforma")
	@ApiResponses({ 
			@ApiResponse(code = 204, message = "Item excluído com sucesso"),
            @ApiResponse(code = 400, message = "Produtos não encontrados com o IDs informados", response = Problem.class),
            @ApiResponse(code = 404, message = "Pedido não encontrado com o ID informado", response = Problem.class)
			})
	@DeleteMapping(value="/{id}/items")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id, @RequestBody @Valid ItemPedidoInput input) {
		this.controller.remover(id, input);
	}

    @ApiOperation(" Consultar o status de pagamento do pedido, informando se o pagamento foi aprovado ou não")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Status de pagamento do pedido"),
            @ApiResponse(code = 404, message = "Pedido não encontrado com o ID informado", response = Problem.class)

    })
    @GetMapping(value = "/{id}/pagamento-status")
    public String buscarStatusDePagamentoDoPedido(@ApiParam(value = "ID do pedido", example = "12345678") @PathVariable Long id) {
    	return this.buscarStatusDePagamentoDoPedido(id);
    }
}

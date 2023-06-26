package com.techchallenge.adapter.driver;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techchallenge.adapter.driver.exceptionhandler.Problem;
import com.techchallenge.adapter.driver.model.ItemPedidoModel;
import com.techchallenge.adapter.driver.model.PedidoModel;
import com.techchallenge.adapter.driver.model.input.ItemPedidoInput;
import com.techchallenge.adapter.driver.model.input.PedidoInput;
import com.techchallenge.adapter.mapper.ItemPedidoMapper;
import com.techchallenge.adapter.mapper.PedidoMapper;
import com.techchallenge.core.applications.service.ItemPedidoService;
import com.techchallenge.core.applications.service.PedidoService;
import com.techchallenge.core.domain.ItemPedido;
import com.techchallenge.core.domain.Pedido;
import com.techchallenge.core.domain.StatusPedido;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Pedidos")
@RestController
@RequestMapping(value = "/pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PedidoController {

    @Autowired
    private PedidoService service;
    
    @Autowired
    private ItemPedidoService itemPedidoService;

    @Autowired
    private PedidoMapper mapper;
    
    @Autowired
    private ItemPedidoMapper itemPedidoMapper;

    @ApiOperation("Consulta pedido pelo ID do pedido")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Pedido"),
            @ApiResponse(code = 404, message = "Pedido não encontrado com o ID informado", response = Problem.class)

    })
    @GetMapping(value = "/{id}")
    public PedidoModel buscarPedidosPorId(@ApiParam(value = "ID do pedido", example = "12345678") @PathVariable Long id) {
        Pedido pedido = service.buscarPedidoPorId(id);
        return mapper.toModel(pedido);
    }

    @ApiOperation("Lista os pedidos, podendo filtrar pelo status")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Listagem de pedidos"),
    })
    @GetMapping
    public Collection<PedidoModel> listarPedidos(@ApiParam(value = "Status dos pedidos", example = "PREPARACAO") @RequestParam(required = false) String status) {
        if (status != null) {
            StatusPedido statusPedido = StatusPedido.valueOf(status);
            List<Pedido> pedidos = service.buscarPedidosPorStatus(statusPedido);
            return mapper.toCollectionModel(pedidos);
        } else {
            List<Pedido> pedidos = service.buscarPedidos();
            return mapper.toCollectionModel(pedidos);
        }
    }

    @ApiOperation("Atualiza o status do pedido")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Status do pedido atualizado"),
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(value = "/{id}/status")
    public void atualizarStatusDoPedido(@ApiParam(value = "ID do pedido", example = "12345678") @PathVariable Long id, @RequestBody String novoStatus) {
        Pedido pedido = service.buscarPedidoPorId(id);
        StatusPedido statusPedido = StatusPedido.valueOf(novoStatus);
        service.atualizarStatusDoPedido(pedido, statusPedido);
    }
    
	@ApiOperation("Inicializa um pedido na plataforma")
	@ApiResponses({ 
			@ApiResponse(code = 201, message = "Pedido inicializado com sucesso") 
			})
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoModel inicializar(@RequestBody @Valid PedidoInput input) {
		Pedido pedido = mapper.toDomainObject(input);
		pedido = service.inicializar(pedido);

		return mapper.toModel(pedido);
	}
	
	@ApiOperation("Adicionar itens ao pedido na plataforma")
	@ApiResponses({ 
			@ApiResponse(code = 201, message = "Itens atualizados com sucesso") 
			})
	@PostMapping(value = "/{id}/items")
	@ResponseStatus(HttpStatus.OK)
	public ItemPedidoModel adicionarProduto(@PathVariable Long id, @RequestBody @Valid ItemPedidoInput input) {
		ItemPedido itemPedido = itemPedidoMapper.toDomainObject(input);
		itemPedido = itemPedidoService.adicionarItemAoPedido(id, itemPedido);

		return itemPedidoMapper.toModel(itemPedido);
	}

}

package com.techchallenge.adapter.driver;

import com.techchallenge.adapter.driver.exceptionhandler.Problem;
import com.techchallenge.adapter.driver.model.PedidoModel;
import com.techchallenge.adapter.mapper.PedidoMapper;
import com.techchallenge.core.applications.service.PedidoService;
import com.techchallenge.core.domain.Pedido;
import com.techchallenge.core.domain.StatusPedido;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@Api(tags = "Pedidos")
@RestController
@RequestMapping(value = "/pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PedidoController {

    @Autowired
    private PedidoService service;

    @Autowired
    private PedidoMapper mapper;

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

}

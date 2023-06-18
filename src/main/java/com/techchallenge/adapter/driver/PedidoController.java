package com.techchallenge.adapter.driver;

import com.techchallenge.adapter.driver.model.PedidoModel;
import com.techchallenge.adapter.mapper.PedidoMapper;
import com.techchallenge.core.applications.service.PedidoService;
import com.techchallenge.core.domain.Pedido;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @ApiOperation("Consulta pedidos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Lista com os pedidos em preparação"),
    })
    @GetMapping
    public Collection<PedidoModel> buscarPedidos() {
        List<Pedido> pedidos = service.buscarPedidos();
        return mapper.toCollectionModel(pedidos);
    }

}

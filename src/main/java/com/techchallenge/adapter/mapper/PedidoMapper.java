package com.techchallenge.adapter.mapper;

import java.util.Collection;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techchallenge.adapter.driver.model.PedidoModel;
import com.techchallenge.adapter.driver.model.input.PedidoInput;
import com.techchallenge.core.domain.Cliente;
import com.techchallenge.core.domain.Pedido;

@Component
public class PedidoMapper {

    @Autowired
    private ModelMapper mapper;

    public Pedido toDomainObject(PedidoInput input) {
        Pedido pedido = mapper.map(input, Pedido.class);
        
        pedido.setCliente(new Cliente());
        pedido.getCliente().setId(input.getClienteId());
        
        return pedido;
    }

    public PedidoModel toModel(Pedido pedido) {
        return mapper.map(pedido, PedidoModel.class);
    }

    public Collection<PedidoModel> toCollectionModel(Collection<Pedido> pedidos) {
        return pedidos.stream()
                .map(c -> mapper.map(c, PedidoModel.class))
                .collect(Collectors.toList());
    }

}

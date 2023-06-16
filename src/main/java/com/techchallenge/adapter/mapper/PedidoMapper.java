package com.techchallenge.adapter.mapper;

import com.techchallenge.adapter.driver.model.ClienteModel;
import com.techchallenge.adapter.driver.model.PedidoModel;
import com.techchallenge.adapter.driver.model.input.ClienteInput;
import com.techchallenge.adapter.driver.model.input.PedidoInput;
import com.techchallenge.core.domain.Cliente;
import com.techchallenge.core.domain.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class PedidoMapper {

    @Autowired
    private ModelMapper mapper;

    public Pedido toDomainObject(PedidoInput input) {
        return mapper.map(input, Pedido.class);
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

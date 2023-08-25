package com.techchallenge.adapter.gateways;

import com.techchallenge.core.domain.entities.ItemPedido;

public interface ItemPedidoGateway {

    ItemPedido adicionarItemAoPedido(Long pedidoId, ItemPedido itemPedido);
    void atualizarItemAoPedido(Long pedidoId, ItemPedido itemPedido);
    void excluirItemAoPedido(Long pedidoId, Long produtoId);
}

package com.techchallenge.core.domain.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techchallenge.adapter.gateways.ItemPedidoGateway;
import com.techchallenge.core.domain.entities.ItemPedido;

@Service
public class ItemPedidoUseCase {

	@Autowired
    private ItemPedidoGateway gateway;
	
    public ItemPedido adicionarItemAoPedido(Long pedidoId, ItemPedido itemPedido) {
    	return this.gateway.adicionarItemAoPedido(pedidoId, itemPedido);
    }
    
    public void atualizarItemAoPedido(Long pedidoId, ItemPedido itemPedido) {
    	this.gateway.atualizarItemAoPedido(pedidoId, itemPedido);
    }
    
    public void excluirItemAoPedido(Long pedidoId, Long produtoId) {
    	this.gateway.excluirItemAoPedido(pedidoId, produtoId);
    }
}
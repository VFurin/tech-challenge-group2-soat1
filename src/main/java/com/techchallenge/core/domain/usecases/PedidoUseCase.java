package com.techchallenge.core.domain.usecases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techchallenge.adapter.gateways.PedidoGateway;
import com.techchallenge.core.domain.entities.Pedido;
import com.techchallenge.core.domain.entities.StatusPedido;

@Service
public class PedidoUseCase {

	@Autowired
    private PedidoGateway gateway;
    
    public List<Pedido> buscarPedidos() {
    	return this.gateway.buscarPedidos();
    }

    public Pedido buscarPedidoPorId(Long id) {
    	return this.gateway.buscarPedidoPorId(id);
    }

    public List<Pedido> buscarPedidosPorStatus(StatusPedido statusPedido) {
    	return this.gateway.buscarPedidosPorStatus(statusPedido);
    }
    
    public Pedido buscarPedidoPorIdEStatus(Long id, StatusPedido statusPedido) {
    	return this.gateway.buscarPedidoPorIdEStatus(id, statusPedido);
    }

    public void atualizarStatusDoPedido(Pedido pedido, StatusPedido statusPedido) {
    	this.gateway.atualizarStatusDoPedido(pedido, statusPedido);
    }
    
    public void atualizar(Pedido pedido) {
    	this.gateway.atualizar(pedido);
    }
    
    public void excluir(Long pedidoId) {
    	this.gateway.excluir(pedidoId);
    }
    
    public void atualizarValorTotal(Long pedidoId) {
    	Pedido pedido = this.gateway.buscarPedidoPorId(pedidoId);
    	pedido.calcularValor();
    	
    	this.gateway.atualizar(pedido);
    }
}
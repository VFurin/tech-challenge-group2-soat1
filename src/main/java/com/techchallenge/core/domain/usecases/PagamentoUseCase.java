package com.techchallenge.core.domain.usecases;

import java.util.List;

import com.techchallenge.adapter.gateways.PedidoGateway;
import com.techchallenge.core.domain.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techchallenge.adapter.gateways.PagamentoGateway;

@Service
public class PagamentoUseCase {
	
	@Autowired
	private PagamentoGateway gateway;

	@Autowired
	private PedidoGateway pedidoGateway;

	public void efetuarPagamento(Long pedidoId, TipoPagamento tipoPagamento) {
		gateway.efetuarPagamento(pedidoId, tipoPagamento);
	}
	
	public List<TipoPagamento> listar() {
		return gateway.listar();
	}

	public void confirmarPagamento(Long pedidoId, EventoPagamento eventoPagamento) {

		Pedido pedido = pedidoGateway.buscarPedidoPorId(pedidoId);

		if (eventoPagamento.getStatusPagamento() == StatusPagamento.APROVADO) {
			pedido.setStatus(StatusPedido.PREPARACAO);
			pedido.setStatusPagamento(StatusPagamento.APROVADO);
			pedidoGateway.atualizar(pedido);
		}
		pedido.setStatusPagamento(StatusPagamento.RECUSADO);
		pedidoGateway.atualizar(pedido);
	}
}
package com.techchallenge.adapter.gateways.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techchallenge.adapter.gateways.PagamentoGateway;
import com.techchallenge.adapter.gateways.PedidoGateway;
import com.techchallenge.core.domain.entities.TipoPagamento;
import com.techchallenge.drivers.db.repositories.TipoPagamentoRepository;

//@Component
public class PagamentoGatewayImpl implements PagamentoGateway {
	
	@Autowired
	private PedidoGateway pedidoService;
	@Autowired
	private TipoPagamentoRepository tipoPagamentoRepository;
	
	public void efetuarPagamento(Long pedidoId, TipoPagamento tipoPagamento) {
//		Long id = tipoPagamento.getId();
//		Pedido pedido = pedidoService.buscarPedidoPorIdEStatus(pedidoId, StatusPedido.RECEBIDO);
//		
//		tipoPagamento = tipoPagamentoRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(
//                String.format("Não existe um cadastro de tipo de pagamento com código %d", id)));
//		
//		pedido.setStatus(StatusPedido.PREPARACAO);
//		pedido.setTipoPagamento(tipoPagamento);
//		
//		pedidoService.atualizar(pedido);
	}
	
	public List<TipoPagamento> listar() {
//		return tipoPagamentoRepository.findAll();
		return null;
	}
}

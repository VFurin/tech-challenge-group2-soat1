package com.techchallenge.adapter.gateways.impl;

import java.util.List;

import com.techchallenge.core.domain.entities.Pedido;
import com.techchallenge.core.domain.entities.StatusPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techchallenge.adapter.gateways.PagamentoGateway;
import com.techchallenge.adapter.gateways.PedidoGateway;
import com.techchallenge.adapter.mapper.business.TipoPagamentoBusinessMapper;
import com.techchallenge.core.domain.entities.TipoPagamento;
import com.techchallenge.core.domain.exception.EntidadeNaoEncontradaException;
import com.techchallenge.drivers.db.entities.TipoPagamentoEntity;
import com.techchallenge.drivers.db.repositories.TipoPagamentoRepository;

@Component
public class PagamentoGatewayImpl implements PagamentoGateway {
	
	@Autowired
	private PedidoGateway pedidoGateway;
	@Autowired
	private TipoPagamentoRepository tipoPagamentoRepository;
	@Autowired
	private TipoPagamentoBusinessMapper businessMapper;
	
	public void efetuarPagamento(Long pedidoId, TipoPagamento tipoPagamento) {
		Long id = tipoPagamento.getId();
		
		TipoPagamentoEntity entity = tipoPagamentoRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(
                String.format("Não existe um cadastro de tipo de pagamento com código %d", id)));
		
		pedidoGateway.atualizarTipoPagamento(pedidoId, businessMapper.toModel(entity));

		Pedido pedido = pedidoGateway.buscarPedidoPorId(pedidoId);
		pedido.setStatusPagamento(StatusPagamento.PROCESSAMENTO);
	}
	
	public List<TipoPagamento> listar() {
		return businessMapper.toCollectionModel(tipoPagamentoRepository.findAll());
	}
}

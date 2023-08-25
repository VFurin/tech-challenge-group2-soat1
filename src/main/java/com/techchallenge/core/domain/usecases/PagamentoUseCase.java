package com.techchallenge.core.domain.usecases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techchallenge.adapter.gateways.PedidoGateway;
import com.techchallenge.core.domain.entities.TipoPagamento;
import com.techchallenge.drivers.db.repositories.TipoPagamentoRepository;

@Service
public class PagamentoUseCase {
	
	@Autowired
	private PedidoGateway pedidoUseCase;
	@Autowired
	private TipoPagamentoRepository tipoPagamentoRepository;
	
	public void efetuarPagamento(Long pedidoId, TipoPagamento tipoPagamento) {
//		Long id = tipoPagamento.getId();
//		Pedido pedido = pedidoUseCase.buscarPedidoPorIdEStatus(pedidoId, StatusPedido.RECEBIDO);
//		
//		tipoPagamento = tipoPagamentoRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(
//                String.format("Não existe um cadastro de tipo de pagamento com código %d", id)));
//		
//		pedido.setStatus(StatusPedido.PREPARACAO);
//		pedido.setTipoPagamento(tipoPagamento);
//		
//		pedidoUseCase.atualizar(pedido);
	}
	
	public List<TipoPagamento> listar() {
//		return tipoPagamentoRepository.findAll();
		return null;
	}
}

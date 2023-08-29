package com.techchallenge.core.applications.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techchallenge.core.applications.ports.TipoPagamentoRepository;
import com.techchallenge.core.domain.TipoPagamento;
import com.techchallenge.core.domain.entities.Pedido;
import com.techchallenge.core.domain.entities.StatusPedido;
import com.techchallenge.core.domain.exception.EntidadeNaoEncontradaException;
import com.techchallenge.core.domain.usecases.PedidoUseCase;

//@Service
public class PagamentoService {
	
	@Autowired
	private PedidoUseCase pedidoService;
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
////		pedido.setTipoPagamento(tipoPagamento);
//		
//		pedidoService.atualizar(pedido);
	}
	
	public List<TipoPagamento> listar() {
//		return tipoPagamentoRepository.findAll();
		return null;
	}
}

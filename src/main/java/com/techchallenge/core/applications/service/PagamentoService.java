package com.techchallenge.core.applications.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.techchallenge.drivers.db.entities.TipoPagamentoEntity;
import com.techchallenge.drivers.db.repositories.TipoPagamentoRepository;

//@Service
public class PagamentoService {
	
	@Autowired
	private PedidoService pedidoService;
	@Autowired
	private TipoPagamentoRepository tipoPagamentoRepository;
	
	public void efetuarPagamento(Long pedidoId, TipoPagamentoEntity tipoPagamento) {
		Long id = tipoPagamento.getId();
//		Pedido pedido = pedidoService.buscarPedidoPorIdEStatus(pedidoId, StatusPedido.RECEBIDO);
		
//		tipoPagamento = tipoPagamentoRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(
//                String.format("Não existe um cadastro de tipo de pagamento com código %d", id)));
//		
//		pedido.setStatus(StatusPedido.PREPARACAO);
//		pedido.setTipoPagamento(tipoPagamento);
//		
//		pedidoService.atualizar(pedido);
	}
	
	public List<TipoPagamentoEntity> listar() {
		return tipoPagamentoRepository.findAll();
	}
}

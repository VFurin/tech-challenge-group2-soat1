package com.techchallenge.core.domain.usecases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techchallenge.adapter.gateways.PagamentoGateway;
import com.techchallenge.core.domain.entities.TipoPagamento;

@Service
public class PagamentoUseCase {
	
	@Autowired
	private PagamentoGateway gateway;
	
	public void efetuarPagamento(Long pedidoId, TipoPagamento tipoPagamento) {
		gateway.efetuarPagamento(pedidoId, tipoPagamento);
	}
	
	public List<TipoPagamento> listar() {
		return gateway.listar();
	}
}
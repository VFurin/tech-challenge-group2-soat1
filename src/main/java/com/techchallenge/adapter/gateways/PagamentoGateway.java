package com.techchallenge.adapter.gateways;

import java.util.List;

import com.techchallenge.core.domain.entities.TipoPagamento;


public interface PagamentoGateway {

	void efetuarPagamento(Long pedidoId, TipoPagamento tipoPagamento);
	List<TipoPagamento> listar();
}

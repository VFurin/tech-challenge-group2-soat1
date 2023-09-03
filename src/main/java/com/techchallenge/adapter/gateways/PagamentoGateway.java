package com.techchallenge.adapter.gateways;

import java.util.List;

import com.techchallenge.adapter.dto.pagamentos.PagamentoResponseDTO;
import com.techchallenge.adapter.dto.pagamentos.PagamentoPixResponseDTO;
import com.techchallenge.core.domain.entities.TipoPagamento;


public interface PagamentoGateway {

	PagamentoPixResponseDTO efetuarPagamento(Long pedidoId, TipoPagamento tipoPagamento);
	PagamentoResponseDTO consultarPagamento(Long paymentId);
	List<TipoPagamento> listar();
}

package com.techchallenge.core.domain.usecases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techchallenge.adapter.dto.pagamentos.PagamentoPixResponseDTO;
import com.techchallenge.adapter.dto.pagamentos.PagamentoResponseDTO;
import com.techchallenge.adapter.gateways.PagamentoGateway;
import com.techchallenge.adapter.gateways.PedidoGateway;
import com.techchallenge.core.domain.entities.EventoPagamento;
import com.techchallenge.core.domain.entities.StatusPagamento;
import com.techchallenge.core.domain.entities.StatusPedido;
import com.techchallenge.core.domain.entities.TipoPagamento;

@Service
public class PagamentoUseCase {
	
	@Autowired
	private PagamentoGateway gateway;

	@Autowired
	private PedidoGateway pedidoGateway;

	public PagamentoPixResponseDTO efetuarPagamento(Long pedidoId, TipoPagamento tipoPagamento) {
		return gateway.efetuarPagamento(pedidoId, tipoPagamento);
	}
	
	public PagamentoResponseDTO consultarPagamento(Long paymentId) {
		return gateway.consultarPagamento(paymentId);
	}
	
	public List<TipoPagamento> listar() {
		return gateway.listar();
	}

	public void confirmarPagamento(Long pedidoId, EventoPagamento eventoPagamento) {

		// Se encontrarmos uma forma de efetivar via mock o pagamento via API do MP para PIX
		// alteraremos para efetuar a consulta pelo PaymentId e atualizar o status do pagamento
		// com base no retorno recebido da API.
		if (eventoPagamento.getStatusPagamento() == StatusPagamento.APROVADO) {
			pedidoGateway.atualizarStatusDoPedidoEPagamento(pedidoId, StatusPedido.PREPARACAO, StatusPagamento.APROVADO);
		} else {
			pedidoGateway.atualizarStatusPagamento(pedidoId, StatusPagamento.RECUSADO);			
		}
	}
}
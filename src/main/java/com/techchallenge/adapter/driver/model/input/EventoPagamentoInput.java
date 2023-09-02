package com.techchallenge.adapter.driver.model.input;

import com.techchallenge.core.domain.entities.StatusPagamento;

public class EventoPagamentoInput {

	private Long paymentId;

	private StatusPagamento statusPagamento;

	public StatusPagamento getStatusPagamento() {
		return statusPagamento;
	}

	public void setStatusPagamento(StatusPagamento statusPagamento) {
		this.statusPagamento = statusPagamento;
	}

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}
}

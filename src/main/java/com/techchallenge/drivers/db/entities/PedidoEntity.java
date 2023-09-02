package com.techchallenge.drivers.db.entities;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.techchallenge.core.domain.entities.StatusPagamento;
import com.techchallenge.core.domain.entities.StatusPedido;

@Entity(name = "Pedido")
public class PedidoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long paymentId;
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<ItemPedidoEntity> itens;
	private BigDecimal valor;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="tipo_pagamento_id", nullable = true)
	private TipoPagamentoEntity tipoPagamento;
	@Enumerated(EnumType.STRING)
	private StatusPedido status;
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = true)
	private ClienteEntity cliente;

	@Enumerated(EnumType.STRING)
	private StatusPagamento statusPagamento;

	private OffsetDateTime dataSolicitacao;
	private OffsetDateTime dataCancelamento;
	private OffsetDateTime dataFinalizacao;
	
	public List<ItemPedidoEntity> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedidoEntity> itens) {
		this.itens = itens;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public TipoPagamentoEntity getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(TipoPagamentoEntity tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public StatusPedido getStatus() {
		return status;
	}

	public void setStatus(StatusPedido status) {
		this.status = status;
	}

	public ClienteEntity getCliente() {
		return cliente;
	}

	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}

	public OffsetDateTime getDataSolicitacao() {
		return dataSolicitacao;
	}

	public void setDataSolicitacao(OffsetDateTime dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

	public OffsetDateTime getDataCancelamento() {
		return dataCancelamento;
	}

	public void setDataCancelamento(OffsetDateTime dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}

	public OffsetDateTime getDataFinalizacao() {
		return dataFinalizacao;
	}

	public void setDataFinalizacao(OffsetDateTime dataFinalizacao) {
		this.dataFinalizacao = dataFinalizacao;
	}

	public Long getId() {
		return id;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public Long getPaymentId() {
		return paymentId;
	}

	public StatusPagamento getStatusPagamento() {
		return statusPagamento;
	}

	public void setStatusPagamento(StatusPagamento statusPagamento) {
		this.statusPagamento = statusPagamento;
	}
}
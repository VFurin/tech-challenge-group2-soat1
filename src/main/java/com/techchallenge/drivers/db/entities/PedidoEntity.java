package com.techchallenge.drivers.db.entities;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

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

//@Entity(name = "Pedido")
public class PedidoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<ItemPedidoEntity> itens;
	private BigDecimal valor;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="tipo_pagamento_id", nullable = true)
	private TipoPagamentoEntity tipoPagamento;
	@Enumerated(EnumType.STRING)
	private StatusPedidoEntity status;
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = true)
	private ClienteEntity cliente;

	private OffsetDateTime dataSolicitacao;
	private OffsetDateTime dataCancelamento;
	private OffsetDateTime dataFinalizacao;
	
    public void calcularValor() {
    	Optional<BigDecimal> valorTotal = itens.stream()
    			.map((ItemPedidoEntity i) -> i.getPrecoTotal())
    			.reduce((BigDecimal p1, BigDecimal p2) -> p1.add(p2));
    	
    	this.setValor(valorTotal.orElse(BigDecimal.ZERO));
    }

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

	public StatusPedidoEntity getStatus() {
		return status;
	}

	public void setStatus(StatusPedidoEntity status) {
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
}
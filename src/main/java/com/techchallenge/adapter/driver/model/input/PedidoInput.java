package com.techchallenge.adapter.driver.model.input;

import java.util.List;

public class PedidoInput {

	private List<ItemPedidoInput> itens;
	private Long clienteId;

	public List<ItemPedidoInput> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedidoInput> itens) {
		this.itens = itens;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}
}
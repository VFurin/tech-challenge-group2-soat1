package com.techchallenge.adapter.driver.model.input;

public class ItemPedidoInput {

	private Integer quantidade;

	private Long produtoId;

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Long getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(Long produtoId) {
		this.produtoId = produtoId;
	}
}

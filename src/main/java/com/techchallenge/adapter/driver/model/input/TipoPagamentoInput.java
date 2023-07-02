package com.techchallenge.adapter.driver.model.input;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TipoPagamentoInput {

	@JsonProperty(value = "pagamentoId")
	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}

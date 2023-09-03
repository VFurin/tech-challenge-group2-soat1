package com.techchallenge.adapter.driver.model.input;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EventoPagamentoInput {

	private Data data;

	public Data getData() { return data; }

	public void setData(Data data) { this.data = data; }

	public static class Data {
		private Long id;

		public Long getId() { return id; }

		public void setId(Long id) { this.id = id; }
	}
}

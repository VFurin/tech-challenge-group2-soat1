package com.techchallenge.adapter.driver.model.input;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class ClienteAtualizacaoInput {

	@ApiModelProperty(example = "Nome do cliente")
	@JsonProperty(required = true)
	@NotNull
	private String nome;
	
	@ApiModelProperty(example = "cliente-teste@teste.com.br")
	@JsonProperty(required = true)
	@NotNull
	private String email;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}

package com.techchallenge.adapter.driver.model.input;

import io.swagger.annotations.ApiModelProperty;

public class ClienteInput {

	@ApiModelProperty(example = "Nome do cliente")
	private String nome;
	@ApiModelProperty(example = "cliente-teste@teste.com.br")
	private String email;
	@ApiModelProperty(example = "12345678901")
	private Long cpf;

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

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}
}

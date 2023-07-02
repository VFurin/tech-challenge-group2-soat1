package com.techchallenge.adapter.driver.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Cliente")
public class ClienteModel {

	@ApiModelProperty(example = "ID do cliente")
	private Long id;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}

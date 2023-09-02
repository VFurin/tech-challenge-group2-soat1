package com.techchallenge.adapter.dto.cliente;

import javax.validation.constraints.NotNull;

public class ClienteDTO {

    @NotNull
    private String nome;

    @NotNull
    private String email;

    @NotNull
    private ClienteDocumentoDTO documento;

    public void PayerDTO() {
    }

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

    public ClienteDocumentoDTO getDocumento() {
        return documento;
    }

    public void setDocumento(ClienteDocumentoDTO documento) {
        this.documento = documento;
    }
}
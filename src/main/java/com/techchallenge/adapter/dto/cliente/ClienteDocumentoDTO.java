package com.techchallenge.adapter.dto.cliente;

import javax.validation.constraints.NotNull;

public class ClienteDocumentoDTO {
    @NotNull
    private String tipo;

    @NotNull
    private String numero;

    public ClienteDocumentoDTO() {
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
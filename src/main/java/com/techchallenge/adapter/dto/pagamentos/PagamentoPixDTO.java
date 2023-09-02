package com.techchallenge.adapter.dto.pagamentos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.techchallenge.adapter.dto.cliente.ClienteDTO;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class PagamentoPixDTO {
    @NotNull
    private BigDecimal total;

    @NotNull
    @JsonProperty("description")
    private String descricao;

    @NotNull
    private ClienteDTO cliente;

    public PagamentoPixDTO() {
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }
}

package com.techchallenge.adapter.driver.model;

import com.techchallenge.core.domain.StatusPedido;
import com.techchallenge.core.domain.TipoPagamento;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

public class PedidoModel {
    private List<ItemPedidoModel> itens;
    private BigDecimal valor;
    private TipoPagamento tipoPagamento;
    private StatusPedido status;
    private ClienteModel cliente;
    private OffsetDateTime dataSolicitacao;

    private OffsetDateTime dataCancelamento;
    private OffsetDateTime dataFinalizacao;

    public List<ItemPedidoModel> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoModel> itens) {
        this.itens = itens;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public ClienteModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }

    public OffsetDateTime getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(OffsetDateTime dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public OffsetDateTime getDataCancelamento() {
        return dataCancelamento;
    }

    public void setDataCancelamento(OffsetDateTime dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }

    public OffsetDateTime getDataFinalizacao() {
        return dataFinalizacao;
    }

    public void setDataFinalizacao(OffsetDateTime dataFinalizacao) {
        this.dataFinalizacao = dataFinalizacao;
    }
}

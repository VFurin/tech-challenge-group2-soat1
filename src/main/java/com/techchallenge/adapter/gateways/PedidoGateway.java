package com.techchallenge.adapter.gateways;

import java.util.List;

import com.techchallenge.core.domain.entities.Pedido;
import com.techchallenge.core.domain.entities.StatusPagamento;
import com.techchallenge.core.domain.entities.StatusPedido;
import com.techchallenge.core.domain.entities.TipoPagamento;


public interface PedidoGateway {

    List<Pedido> buscarPedidos();
    Pedido buscarPedidoPorId(Long id);
    Pedido buscarPedidoPorPaymentId(Long PaymentId);
    List<Pedido> buscarPedidosPorStatus(StatusPedido statusPedido);
    Pedido buscarPedidoPorIdEStatus(Long id, StatusPedido statusPedido);
    void atualizarStatusDoPedido(Pedido pedido, StatusPedido statusPedido);
    void atualizarStatusPagamento(Long id, StatusPagamento statusPagamento);
    void atualizarTipoPagamento(Long id, TipoPagamento tipoPagamento);
    void atualizarPaymentId(Long id, Long paymentId);
    Pedido gravar(Pedido pedido);
    void excluir(Long pedidoId);
    void atualizarStatusDoPedidoEPagamento(Long pedidoId, StatusPedido statusPedido, StatusPagamento statusPagamento);
}

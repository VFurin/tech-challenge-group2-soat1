package com.techchallenge.core.applications.service;

import com.techchallenge.core.applications.ports.PedidoRepository;
import com.techchallenge.core.domain.Pedido;
import com.techchallenge.core.domain.StatusPedido;
import com.techchallenge.core.domain.exception.NegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> buscarPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido buscarPedidoPorId(Long id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new NegocioException(
                String.format("Não existe pedido com o id %d", id)));
    }

    public List<Pedido> buscarPedidosPorStatus(StatusPedido statusPedido) {
        return pedidoRepository.findByStatus(statusPedido);
    }

    public void atualizarStatusDoPedido(Pedido pedido, StatusPedido statusPedido) {
        pedido.setStatus(statusPedido);
        pedidoRepository.save(pedido);
    }
    
    public Pedido inicializar(Pedido pedido) {
        pedido.setStatus(StatusPedido.GERACAO);
        pedido.setDataSolicitacao(OffsetDateTime.now());
        return pedidoRepository.save(pedido);
    }
}
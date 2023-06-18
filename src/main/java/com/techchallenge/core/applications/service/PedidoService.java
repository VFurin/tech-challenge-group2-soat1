package com.techchallenge.core.applications.service;

import com.techchallenge.core.applications.ports.PedidoRepository;
import com.techchallenge.core.domain.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> buscarPedidos() {
        return pedidoRepository.findAll();
    }

}

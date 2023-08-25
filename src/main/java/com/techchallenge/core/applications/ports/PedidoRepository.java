package com.techchallenge.core.applications.ports;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techchallenge.core.domain.Pedido;
import com.techchallenge.core.domain.StatusPedido;

//@Repository
public interface PedidoRepository { // extends JpaRepository<Pedido, Long> {

    List<Pedido> findByStatus(StatusPedido statusPedido);
    Optional<Pedido> findByIdAndStatus(Long id, StatusPedido statusPedido);

}

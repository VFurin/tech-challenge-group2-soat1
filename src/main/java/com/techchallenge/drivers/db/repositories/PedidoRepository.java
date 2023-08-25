package com.techchallenge.drivers.db.repositories;

import java.util.List;
import java.util.Optional;

import com.techchallenge.drivers.db.entities.PedidoEntity;
import com.techchallenge.drivers.db.entities.StatusPedidoEntity;


// @Repository
public interface PedidoRepository { //extends JpaRepository<Pedido, Long> {

    List<PedidoEntity> findByStatus(StatusPedidoEntity statusPedido);
    Optional<PedidoEntity> findByIdAndStatus(Long id, StatusPedidoEntity statusPedido);

}

package com.techchallenge.core.applications.ports;

import com.techchallenge.core.domain.Pedido;
import com.techchallenge.core.domain.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByStatus(StatusPedido statusPedido);

}

package com.techchallenge.drivers.db.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techchallenge.core.domain.entities.StatusPedido;
import com.techchallenge.drivers.db.entities.PedidoEntity;


@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {

    List<PedidoEntity> findByStatus(StatusPedido statusPedido);
    Optional<PedidoEntity> findByIdAndStatus(Long id, StatusPedido statusPedido);

}

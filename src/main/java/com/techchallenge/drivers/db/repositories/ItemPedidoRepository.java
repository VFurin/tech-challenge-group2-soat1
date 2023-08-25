package com.techchallenge.drivers.db.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.techchallenge.drivers.db.entities.ItemPedidoEntity;


@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedidoEntity, Long> {

	@Query("select i from ItemPedido i where i.pedido.id = :pedidoId and i.produto.id = :produtoId and i.pedido.status = 'RECEBIDO'")
	public List<ItemPedidoEntity> findByPedidoAndProduto(Long pedidoId, Long produtoId);
}

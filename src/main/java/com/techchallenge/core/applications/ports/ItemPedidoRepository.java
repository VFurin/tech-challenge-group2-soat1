package com.techchallenge.core.applications.ports;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.techchallenge.core.domain.ItemPedido;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {

	@Query("select i from ItemPedido i where i.pedido.id = :pedidoId and i.produto.id = :produtoId and i.pedido.status = 'RECEBIDO'")
	public List<ItemPedido> findByPedidoAndProduto(Long pedidoId, Long produtoId);
}

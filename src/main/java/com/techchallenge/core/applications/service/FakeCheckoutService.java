package com.techchallenge.core.applications.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.techchallenge.core.domain.exception.NegocioException;
import com.techchallenge.drivers.db.entities.ClienteEntity;
import com.techchallenge.drivers.db.entities.PedidoEntity;
import com.techchallenge.drivers.db.entities.ProdutoEntity;
import com.techchallenge.drivers.db.entities.StatusPedidoEntity;
import com.techchallenge.drivers.db.repositories.ClienteRepository;
import com.techchallenge.drivers.db.repositories.PedidoRepository;
import com.techchallenge.drivers.db.repositories.ProdutoRepository;

//@Service
public class FakeCheckoutService {
	
	private static final String MSG_PRODUTO_NAO_ENCONTRADO = "Produto não encontrado com código %d";
	private static final String MSG_CLIENTE_NAO_ENCONTRADO = "Cliente não encontrado com código %d";
	
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private ClienteRepository clienteRepository;

	@Transactional
	public PedidoEntity checkout(PedidoEntity pedido) {
		
		this.validarItens(pedido);
		this.validarCliente(pedido);
		
		pedido.setDataSolicitacao(OffsetDateTime.now());
		pedido.setStatus(StatusPedidoEntity.RECEBIDO);
		pedido.calcularValor();
		
		return pedidoRepository.save(pedido);
	}
	
	private void validarItens(PedidoEntity pedido) {
	    pedido.getItens().forEach(item -> {
	    	Long produtoId = item.getProduto().getId();
	    	
	        ProdutoEntity produto = produtoRepository.findById(produtoId)
	        		.orElseThrow(() -> new NegocioException(String.format(MSG_PRODUTO_NAO_ENCONTRADO, produtoId)));
	        
	        item.setPedido(pedido);
	        item.setProduto(produto);
	        item.calcularPrecoTotal();
	    });
	}
	
	private void validarCliente(PedidoEntity pedido) {
		Long clienteId = pedido.getCliente().getId();
		
		ClienteEntity cliente = clienteRepository.findById(clienteId)
				.orElseThrow(() -> new NegocioException(String.format(MSG_CLIENTE_NAO_ENCONTRADO, clienteId)));
				
		pedido.setCliente(cliente);
	}
}

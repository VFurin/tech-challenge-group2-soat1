package com.techchallenge.core.applications.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techchallenge.core.applications.ports.PedidoRepository;
import com.techchallenge.core.applications.ports.ProdutoRepository;
import com.techchallenge.core.domain.Pedido;
import com.techchallenge.core.domain.Produto;
import com.techchallenge.core.domain.StatusPedido;
import com.techchallenge.core.domain.exception.NegocioException;
import com.techchallenge.drivers.db.entities.ClienteEntity;
import com.techchallenge.drivers.db.repositories.ClienteRepository;

@Service
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
	public Pedido checkout(Pedido pedido) {
		
		this.validarItens(pedido);
		this.validarCliente(pedido);
		
		pedido.setDataSolicitacao(OffsetDateTime.now());
		pedido.setStatus(StatusPedido.RECEBIDO);
		pedido.calcularValor();
		
		return pedidoRepository.save(pedido);
	}
	
	private void validarItens(Pedido pedido) {
	    pedido.getItens().forEach(item -> {
	    	Long produtoId = item.getProduto().getId();
	    	
	        Produto produto = produtoRepository.findById(produtoId)
	        		.orElseThrow(() -> new NegocioException(String.format(MSG_PRODUTO_NAO_ENCONTRADO, produtoId)));
	        
	        item.setPedido(pedido);
	        item.setProduto(produto);
	        item.calcularPrecoTotal();
	    });
	}
	
	private void validarCliente(Pedido pedido) {
		Long clienteId = pedido.getCliente().getId();
		
		ClienteEntity cliente = clienteRepository.findById(clienteId)
				.orElseThrow(() -> new NegocioException(String.format(MSG_CLIENTE_NAO_ENCONTRADO, clienteId)));
				
		pedido.setCliente(cliente);
	}
}

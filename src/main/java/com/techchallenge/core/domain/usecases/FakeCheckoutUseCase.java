package com.techchallenge.core.domain.usecases;

import org.springframework.beans.factory.annotation.Autowired;

import com.techchallenge.core.domain.entities.Pedido;

//@Service
public class FakeCheckoutUseCase {
	
	private static final String MSG_PRODUTO_NAO_ENCONTRADO = "Produto não encontrado com código %d";
	private static final String MSG_CLIENTE_NAO_ENCONTRADO = "Cliente não encontrado com código %d";
	
	@Autowired
	private PedidoUseCase pedidoUseCase;
	@Autowired
	private ProdutoUseCase produtoUseCase;
	@Autowired
	private ClienteUseCase clienteUseCase;
	
	public Pedido checkout(Pedido pedido) {
		
//		this.validarItens(pedido);
//		this.validarCliente(pedido);
//		
//		pedido.setDataSolicitacao(OffsetDateTime.now());
//		pedido.setStatus(StatusPedido.RECEBIDO);
//		pedido.calcularValor();
//		
//		return pedidoRepository.save(pedido);
		return null;
	}
	
	private void validarItens(Pedido pedido) {
//	    pedido.getItens().forEach(item -> {
//	    	Long produtoId = item.getProduto().getId();
//	    	
//	        Produto produto = produtoRepository.findById(produtoId)
//	        		.orElseThrow(() -> new NegocioException(String.format(MSG_PRODUTO_NAO_ENCONTRADO, produtoId)));
//	        
//	        item.setPedido(pedido);
//	        item.setProduto(produto);
//	        item.calcularPrecoTotal();
//	    });
	}
	
	private void validarCliente(Pedido pedido) {
//		Long clienteId = pedido.getCliente().getId();
//		
//		Cliente cliente = clienteRepository.findById(clienteId)
//				.orElseThrow(() -> new NegocioException(String.format(MSG_CLIENTE_NAO_ENCONTRADO, clienteId)));
//				
//		pedido.setCliente(cliente);
	}
}

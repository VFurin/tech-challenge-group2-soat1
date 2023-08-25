package com.techchallenge.core.applications.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.techchallenge.core.domain.exception.EntidadeNaoEncontradaException;
import com.techchallenge.core.domain.exception.NegocioException;
import com.techchallenge.drivers.db.entities.ItemPedidoEntity;
import com.techchallenge.drivers.db.entities.PedidoEntity;
import com.techchallenge.drivers.db.entities.ProdutoEntity;
import com.techchallenge.drivers.db.entities.StatusPedidoEntity;
import com.techchallenge.drivers.db.repositories.ItemPedidoRepository;
import com.techchallenge.drivers.db.repositories.PedidoRepository;
import com.techchallenge.drivers.db.repositories.ProdutoRepository;

//@Service
public class ItemPedidoService {

	private static final String MSG_ITEM_NAO_ENCONTRADO = "Item não encontrado no pedido com o id %d e produto com o id %d";
	private static final String MSG_ITEM_JA_ADICIONADO = "Item informado no pedido com o id %d e produto com o id %d já existente";
	private static final String MSG_PEDIDO_NAO_ENCONTRADO = "Pedido com o id %d não encontrado com o status RECEBIDO";
	private static final String MSG_PRODUTO_NAO_ENCONTRADO = "Produto com o id %d não encontrado";
	
    @Autowired
    private PedidoRepository pedidoRepository;
    
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;
    
    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public ItemPedidoEntity adicionarItemAoPedido(Long pedidoId, ItemPedidoEntity itemPedido) {
    	Long produtoId = itemPedido.getProduto().getId();
    	
    	PedidoEntity pedido = pedidoRepository.findByIdAndStatus(pedidoId, StatusPedidoEntity.RECEBIDO)
    			.orElseThrow(() -> new NegocioException(String.format(MSG_PEDIDO_NAO_ENCONTRADO, pedidoId)));
    	
    	ProdutoEntity produto = produtoRepository.findById(itemPedido.getProduto().getId())
    			.orElseThrow(() -> new NegocioException(String.format(MSG_PRODUTO_NAO_ENCONTRADO, produtoId)));
    	
    	List<ItemPedidoEntity> itens = itemPedidoRepository.findByPedidoAndProduto(pedidoId, produtoId);
    	
    	if (!itens.isEmpty()) {
    		throw new NegocioException(String.format(MSG_ITEM_JA_ADICIONADO, pedidoId, produtoId));
    	}
    	
    	pedido.getItens().add(itemPedido);
    	
    	itemPedido.setPedido(pedido);
    	itemPedido.setProduto(produto);
    	itemPedido.calcularPrecoTotal();
    	
    	pedido.calcularValor();
    	
    	return itemPedidoRepository.save(itemPedido);
    }
    
    @Transactional
    public void atualizarItemAoPedido(Long pedidoId, ItemPedidoEntity itemPedido) {
    	Long produtoId = itemPedido.getProduto().getId();
    	List<ItemPedidoEntity> itens = itemPedidoRepository.findByPedidoAndProduto(pedidoId, produtoId);
    	
    	ItemPedidoEntity item = itens.stream().findFirst()
    			.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(MSG_ITEM_NAO_ENCONTRADO, pedidoId, produtoId)));
    	
    	item.setQuantidade(itemPedido.getQuantidade());
    	item.calcularPrecoTotal();
    	item.getPedido().calcularValor();
    }
    
    @Transactional
    public void excluirItemAoPedido(Long pedidoId, Long produtoId) {
    	List<ItemPedidoEntity> itens = itemPedidoRepository.findByPedidoAndProduto(pedidoId, produtoId);
    	
    	ItemPedidoEntity item = itens.stream().findFirst()
    			.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(MSG_ITEM_NAO_ENCONTRADO, pedidoId, produtoId)));

    	itemPedidoRepository.deleteById(item.getId());
    	
    	PedidoEntity pedido = pedidoRepository.findById(pedidoId).get();
    	itemPedidoRepository.flush();
    	
    	pedido.calcularValor();
    	
    }
}
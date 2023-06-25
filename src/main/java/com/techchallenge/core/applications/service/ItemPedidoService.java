package com.techchallenge.core.applications.service;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techchallenge.core.applications.ports.ItemPedidoRepository;
import com.techchallenge.core.applications.ports.PedidoRepository;
import com.techchallenge.core.applications.ports.ProdutoRepository;
import com.techchallenge.core.domain.ItemPedido;
import com.techchallenge.core.domain.Pedido;
import com.techchallenge.core.domain.Produto;
import com.techchallenge.core.domain.StatusPedido;
import com.techchallenge.core.domain.exception.NegocioException;

@Service
public class ItemPedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;
    
    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Pedido> buscarPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido buscarPedidoPorId(Long id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new NegocioException(
                String.format("Não existe pedido com o id %d", id)));
    }

    public List<Pedido> buscarPedidosPorStatus(StatusPedido statusPedido) {
        return pedidoRepository.findByStatus(statusPedido);
    }

    public void atualizarStatusDoPedido(Pedido pedido, StatusPedido statusPedido) {
        pedido.setStatus(statusPedido);
        pedidoRepository.save(pedido);
    }
    
    public Pedido inicializar(Pedido pedido) {
        pedido.setStatus(StatusPedido.GERACAO);
        pedido.setDataSolicitacao(OffsetDateTime.now());
        return pedidoRepository.save(pedido);
    }
    
    public ItemPedido adicionarItemAoPedido(Long idPedido, ItemPedido itemPedido) {
    	Pedido pedido = pedidoRepository.findById(idPedido).get();
    	Produto produto = produtoRepository.findById(itemPedido.getProduto().getId()).get();
    	
    	pedido.getItens().add(itemPedido);
    	
    	itemPedido.setPedido(pedido);
    	itemPedido.setProduto(produto);
    	itemPedido.calcularPrecoTotal();
    	
    	return itemPedidoRepository.save(itemPedido);
    }
}
package com.techchallenge.core.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.techchallenge.TestConfig;
import com.techchallenge.adapter.gateways.PedidoGateway;
import com.techchallenge.core.domain.TipoPagamento;
import com.techchallenge.core.domain.entities.Categoria;
import com.techchallenge.core.domain.entities.Cliente;
import com.techchallenge.core.domain.entities.ItemPedido;
import com.techchallenge.core.domain.entities.Pedido;
import com.techchallenge.core.domain.entities.Produto;
import com.techchallenge.core.domain.entities.StatusPedido;

@SpringBootTest
@ContextConfiguration(classes = {TestConfig.class}, loader = AnnotationConfigContextLoader.class)
public class PedidoServiceTest {

    @MockBean
    private PedidoGateway service;

    @Test
    void dadoUmaListaDePedidosQuandoBuscarPedidosEntaoRetornaPedidos() {
    	Cliente clienteMock = new Cliente();
        clienteMock.setNome("Teste");
        clienteMock.setEmail("teste@teste.com");
        clienteMock.setCpf(123456789L);

        Categoria categoriaMock = new Categoria();
        categoriaMock.setNome("Categoria");
        categoriaMock.setId(123L);

        Produto ProdutoMock = new Produto();
        ProdutoMock.setNome("Produto");
        ProdutoMock.setCategoria(categoriaMock);
        ProdutoMock.setId(123456L);
        ProdutoMock.setPreco(BigDecimal.valueOf(10));
        ProdutoMock.setDescricao("Descricao");
        ProdutoMock.setImagem("Imagem");

        ItemPedido itemPedidoMock = new ItemPedido();
        itemPedidoMock.setPedido(new Pedido());
        itemPedidoMock.setProduto(ProdutoMock);
        itemPedidoMock.setQuantidade(1);
        itemPedidoMock.setPrecoTotal(BigDecimal.valueOf(10));

        List<ItemPedido> itensPedidosMock = new ArrayList<>();
        itensPedidosMock.add(itemPedidoMock);

        TipoPagamento tipoPagamento = new TipoPagamento();
        tipoPagamento.setNome("QRCODE");

        Pedido pedidoMock = new Pedido();
        pedidoMock.setCliente(clienteMock);
        pedidoMock.setItens(itensPedidosMock);
        pedidoMock.setStatus(StatusPedido.PREPARACAO);
        pedidoMock.setDataFinalizacao(OffsetDateTime.now().plusHours(1));
        pedidoMock.setDataSolicitacao(OffsetDateTime.now());
//        pedidoMock.setTipoPagamento(tipoPagamento);
        pedidoMock.setValor(BigDecimal.valueOf(10));


        List<Pedido> pedidosMock = new ArrayList<>();

        pedidosMock.add(pedidoMock);


        when(service.buscarPedidos()).thenReturn(pedidosMock);

        List<Pedido> pedidosRetornados = service.buscarPedidos();

        assertEquals(pedidosMock.get(0).getCliente().getCpf(), pedidosRetornados.get(0).getCliente().getCpf());
        assertEquals(pedidosMock.get(0).getCliente().getEmail(), pedidosRetornados.get(0).getCliente().getEmail());
        assertEquals(pedidosMock.get(0).getCliente().getNome(), pedidosRetornados.get(0).getCliente().getNome());
        assertEquals(pedidosMock.get(0).getDataSolicitacao(), pedidosRetornados.get(0).getDataSolicitacao());
        assertEquals(pedidosMock.get(0).getDataFinalizacao(), pedidosRetornados.get(0).getDataFinalizacao());
        assertEquals(pedidosMock.get(0).getDataCancelamento(), pedidosRetornados.get(0).getDataCancelamento());
        assertEquals(pedidosMock.get(0).getStatus(), pedidosRetornados.get(0).getStatus());
        assertEquals(pedidosMock.get(0).getValor(), pedidosRetornados.get(0).getValor());
        assertEquals(pedidosMock.get(0).getTipoPagamento().getNome(), pedidosRetornados.get(0).getTipoPagamento().getNome());
        assertEquals(pedidosMock.get(0).getItens().get(0).getPrecoTotal(), pedidosRetornados.get(0).getItens().get(0).getPrecoTotal());

        verify(service, times(1)).buscarPedidos();
    }

    @Test
    void dadoUmaListaDePedidosQuandoBuscarPedidoPorIdEntaoRetornaPedido() {
        Cliente clienteMock1 = new Cliente();
        clienteMock1.setNome("Teste");
        clienteMock1.setEmail("teste@teste.com");
        clienteMock1.setCpf(123456789L);

        Categoria categoriaMock1 = new Categoria();
        categoriaMock1.setNome("Categoria");
        categoriaMock1.setId(123L);

        Produto ProdutoMock1 = new Produto();
        ProdutoMock1.setNome("Produto");
        ProdutoMock1.setCategoria(categoriaMock1);
        ProdutoMock1.setId(123456L);
        ProdutoMock1.setPreco(BigDecimal.valueOf(10));
        ProdutoMock1.setDescricao("Descricao");
        ProdutoMock1.setImagem("Imagem");

        ItemPedido itemPedidoMock1 = new ItemPedido();
        itemPedidoMock1.setPedido(new Pedido());
        itemPedidoMock1.setProduto(ProdutoMock1);
        itemPedidoMock1.setQuantidade(1);
        itemPedidoMock1.setPrecoTotal(BigDecimal.valueOf(10));

        List<ItemPedido> itensPedidosMock1 = new ArrayList<>();
        itensPedidosMock1.add(itemPedidoMock1);

        TipoPagamento tipoPagamento = new TipoPagamento();
        tipoPagamento.setNome("QRCODE");

        Pedido pedidoMock1 = new Pedido();
        pedidoMock1.setCliente(clienteMock1);
        pedidoMock1.setItens(itensPedidosMock1);
        pedidoMock1.setStatus(StatusPedido.PREPARACAO);
        pedidoMock1.setDataFinalizacao(OffsetDateTime.now().plusHours(1));
        pedidoMock1.setDataSolicitacao(OffsetDateTime.now());
//        pedidoMock1.setTipoPagamento(tipoPagamento);
        pedidoMock1.setValor(BigDecimal.valueOf(10));

        Cliente clienteMock2 = new Cliente();
        clienteMock2.setNome("Teste");
        clienteMock2.setEmail("teste@teste.com");
        clienteMock2.setCpf(3332123L);

        Categoria categoriaMock2 = new Categoria();
        categoriaMock2.setNome("Categoria");
        categoriaMock2.setId(123L);

        Produto ProdutoMock2 = new Produto();
        ProdutoMock2.setNome("Produto");
        ProdutoMock2.setCategoria(categoriaMock2);
        ProdutoMock2.setId(222L);
        ProdutoMock2.setPreco(BigDecimal.valueOf(30));
        ProdutoMock2.setDescricao("Descricao");
        ProdutoMock2.setImagem("Imagem");

        ItemPedido itemPedidoMock2 = new ItemPedido();
        itemPedidoMock2.setPedido(new Pedido());
        itemPedidoMock2.setProduto(ProdutoMock2);
        itemPedidoMock2.setQuantidade(2);
        itemPedidoMock2.setPrecoTotal(BigDecimal.valueOf(20));

        List<ItemPedido> itensPedidosMock2 = new ArrayList<>();
        itensPedidosMock2.add(itemPedidoMock2);

        Pedido pedidoMock2 = new Pedido();
        pedidoMock2.setCliente(clienteMock2);
        pedidoMock2.setItens(itensPedidosMock2);
        pedidoMock2.setStatus(StatusPedido.PREPARACAO);
        pedidoMock2.setDataFinalizacao(OffsetDateTime.now().plusHours(1));
        pedidoMock2.setDataSolicitacao(OffsetDateTime.now());
//        pedidoMock2.setTipoPagamento(tipoPagamento);
        pedidoMock2.setValor(BigDecimal.valueOf(10));

        List<Pedido> pedidosMock = new ArrayList<>();

        pedidosMock.add(pedidoMock1);
        pedidosMock.add(pedidoMock2);

        Pedido pedidoMock = pedidosMock.get(0);


        when(service.buscarPedidoPorId(123L)).thenReturn(pedidoMock);

        Pedido pedidoRetornado = service.buscarPedidoPorId(123L);

        assertEquals(pedidosMock.get(0).getCliente().getCpf(), pedidoRetornado.getCliente().getCpf());
        assertEquals(pedidosMock.get(0).getCliente().getEmail(), pedidoRetornado.getCliente().getEmail());
        assertEquals(pedidosMock.get(0).getCliente().getNome(), pedidoRetornado.getCliente().getNome());
        assertEquals(pedidosMock.get(0).getDataSolicitacao(), pedidoRetornado.getDataSolicitacao());
        assertEquals(pedidosMock.get(0).getDataFinalizacao(), pedidoRetornado.getDataFinalizacao());
        assertEquals(pedidosMock.get(0).getDataCancelamento(), pedidoRetornado.getDataCancelamento());
        assertEquals(pedidosMock.get(0).getStatus(), pedidoRetornado.getStatus());
        assertEquals(pedidosMock.get(0).getValor(), pedidoRetornado.getValor());
        assertEquals(pedidosMock.get(0).getTipoPagamento().getNome(), pedidoRetornado.getTipoPagamento().getNome());
        assertEquals(pedidosMock.get(0).getItens().get(0).getPrecoTotal(), pedidoRetornado.getItens().get(0).getPrecoTotal());

        verify(service, times(1)).buscarPedidoPorId(123L);
    }

    @Test
    void dadoUmaListaDePedidosQuandoBuscarPedidosPorStatusEntaoRetornaPedidos() {
        StatusPedido statusPedido = StatusPedido.PREPARACAO;

        Cliente clienteMock = new Cliente();
        clienteMock.setNome("Teste");
        clienteMock.setEmail("teste@teste.com");
        clienteMock.setCpf(123456789L);

        Categoria categoriaMock = new Categoria();
        categoriaMock.setNome("Categoria");
        categoriaMock.setId(123L);

        Produto ProdutoMock = new Produto();
        ProdutoMock.setNome("Produto");
        ProdutoMock.setCategoria(categoriaMock);
        ProdutoMock.setId(123456L);
        ProdutoMock.setPreco(BigDecimal.valueOf(10));
        ProdutoMock.setDescricao("Descricao");
        ProdutoMock.setImagem("Imagem");

        ItemPedido itemPedidoMock = new ItemPedido();
        itemPedidoMock.setPedido(new Pedido());
        itemPedidoMock.setProduto(ProdutoMock);
        itemPedidoMock.setQuantidade(1);
        itemPedidoMock.setPrecoTotal(BigDecimal.valueOf(10));

        List<ItemPedido> itensPedidosMock = new ArrayList<>();
        itensPedidosMock.add(itemPedidoMock);

        TipoPagamento tipoPagamento = new TipoPagamento();
        tipoPagamento.setNome("QRCODE");

        Pedido pedidoMock = new Pedido();
        pedidoMock.setCliente(clienteMock);
        pedidoMock.setItens(itensPedidosMock);
        pedidoMock.setStatus(StatusPedido.PREPARACAO);
        pedidoMock.setDataFinalizacao(OffsetDateTime.now().plusHours(1));
        pedidoMock.setDataSolicitacao(OffsetDateTime.now());
//        pedidoMock.setTipoPagamento(tipoPagamento);
        pedidoMock.setValor(BigDecimal.valueOf(10));


        List<Pedido> pedidosMock = new ArrayList<>();

        pedidosMock.add(pedidoMock);


        when(service.buscarPedidosPorStatus(statusPedido)).thenReturn(pedidosMock);

        List<Pedido> pedidosRetornados = service.buscarPedidosPorStatus(statusPedido);

        assertEquals(pedidosMock.get(0).getCliente().getCpf(), pedidosRetornados.get(0).getCliente().getCpf());
        assertEquals(pedidosMock.get(0).getCliente().getEmail(), pedidosRetornados.get(0).getCliente().getEmail());
        assertEquals(pedidosMock.get(0).getCliente().getNome(), pedidosRetornados.get(0).getCliente().getNome());
        assertEquals(pedidosMock.get(0).getDataSolicitacao(), pedidosRetornados.get(0).getDataSolicitacao());
        assertEquals(pedidosMock.get(0).getDataFinalizacao(), pedidosRetornados.get(0).getDataFinalizacao());
        assertEquals(pedidosMock.get(0).getDataCancelamento(), pedidosRetornados.get(0).getDataCancelamento());
        assertEquals(pedidosMock.get(0).getStatus(), pedidosRetornados.get(0).getStatus());
        assertEquals(pedidosMock.get(0).getValor(), pedidosRetornados.get(0).getValor());
        assertEquals(pedidosMock.get(0).getTipoPagamento().getNome(), pedidosRetornados.get(0).getTipoPagamento().getNome());
        assertEquals(pedidosMock.get(0).getItens().get(0).getPrecoTotal(), pedidosRetornados.get(0).getItens().get(0).getPrecoTotal());

        verify(service, times(1)).buscarPedidosPorStatus(statusPedido);
    }
}

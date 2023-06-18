package com.techchallenge.core.service;

import com.techchallenge.TestConfig;
import com.techchallenge.core.applications.service.PedidoService;
import com.techchallenge.core.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration(classes = {TestConfig.class}, loader = AnnotationConfigContextLoader.class)
public class PedidoServiceTest {

    @MockBean
    private PedidoService service;

    @Test
    void dadoUmaListaDePedidosQuandoBuscarPedidosEntaoRetornaPedidos() {
        Cliente clienteMock = new Cliente();
        clienteMock.setNome("Teste");
        clienteMock.setEmail("teste@teste.com");
        clienteMock.setCpf(123456789L);

        Categoria categoriaMock = new Categoria();
        categoriaMock.setNome("Categoria");
        categoriaMock.setId(123L);

        Produto produtoMock = new Produto();
        produtoMock.setNome("Produto");
        produtoMock.setCategoria(categoriaMock);
        produtoMock.setId(123456L);
        produtoMock.setPreco(BigDecimal.valueOf(10));
        produtoMock.setDescricao("Descricao");
        produtoMock.setImagem("Imagem");

        ItemPedido itemPedidoMock = new ItemPedido();
        itemPedidoMock.setPedido(new Pedido());
        itemPedidoMock.setProduto(produtoMock);
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
        pedidoMock.setTipoPagamento(tipoPagamento);
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

}

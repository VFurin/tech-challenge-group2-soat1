package com.techchallenge.core.service;

import com.techchallenge.adapter.driver.model.input.ClienteInput;
import com.techchallenge.core.applications.service.ClienteService;
import com.techchallenge.core.domain.Cliente;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ClienteServiceTests {

    @MockBean
    private ClienteService service;

    @Test
    void testDeveSalvarClienteComClienteInputValido() {
        ClienteInput clienteInput = new ClienteInput();
        clienteInput.setNome("Teste");
        clienteInput.setEmail("teste@teste.com");
        clienteInput.setCpf(12345678911L);

        Cliente clienteMock = new Cliente();
        clienteMock.setNome(clienteInput.getNome());
        clienteMock.setEmail(clienteInput.getEmail());
        clienteMock.setCpf(clienteInput.getCpf());
        when(service.salvar(any(Cliente.class))).thenReturn(clienteMock);

        assertNotNull(clienteInput);
        assertEquals(clienteMock.getNome(), clienteInput.getNome());
        assertEquals(clienteMock.getEmail(), clienteInput.getEmail());
        assertEquals(clienteMock.getCpf(), clienteInput.getCpf());
    }

    @Test
    void testDeveBuscarClientePorCpfERetornarOClienteValido() {
        Long cpf = 12345678911L;

        Cliente clienteMock = new Cliente();
        clienteMock.setNome("Teste");
        clienteMock.setEmail("teste@teste.com");
        clienteMock.setCpf(cpf);
        List<Cliente> clientesMock = new ArrayList<>();
        clientesMock.add(clienteMock);

        when(service.buscarPorCpf(cpf)).thenReturn(clientesMock);

        Cliente clienteRetornado = service.buscarPorCpf(cpf)
                .stream()
                .findFirst()
                .orElse(null);

        assertNotNull(clienteRetornado);
        assertEquals(clienteMock.getId(), clienteRetornado.getId());
        assertEquals(clienteMock.getNome(), clienteRetornado.getNome());
        assertEquals(clienteMock.getEmail(), clienteRetornado.getEmail());
        assertEquals(clienteMock.getCpf(), clienteRetornado.getCpf());

        verify(service, times(1)).buscarPorCpf(cpf);
    }
}
package com.techchallenge.adapter.gateways;

import java.util.List;

import com.techchallenge.core.domain.entities.Cliente;


public interface ClienteGateway {

    Cliente salvar(Cliente cliente);
    Cliente buscarPorId(Long id);
    List<Cliente> buscarPorCpf(Long cpf);
    void excluir(Long clienteId) ;
    void atualizarDadosCliente(Long id, Cliente cliente);
}

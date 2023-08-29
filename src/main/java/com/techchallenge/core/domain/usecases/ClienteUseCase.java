package com.techchallenge.core.domain.usecases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techchallenge.adapter.gateways.ClienteGateway;
import com.techchallenge.core.domain.entities.Cliente;
import com.techchallenge.core.domain.exception.NegocioException;

@Service
public class ClienteUseCase {
	
	@Autowired
    private ClienteGateway gateway;
	
    
    public Cliente salvar(Cliente cliente) {
    	try {
    		return gateway.salvar(cliente);	
    	} catch (NegocioException e) {
    		throw e;
    	}
    }

    public List<Cliente> buscarPorCpf(Long cpf) {
        return gateway.buscarPorCpf(cpf);
    }
    
    public void excluir(Long clienteId) {
    	try {
    		gateway.excluir(clienteId);
		} catch (NegocioException e) {
			throw e;
		}
    }
    
    public void atualizarDadosCliente(Long id, Cliente cliente) {
    	try {
    		this.gateway.atualizarDadosCliente(id, cliente);
		} catch (NegocioException e) {
			throw e;
		}
    }
}
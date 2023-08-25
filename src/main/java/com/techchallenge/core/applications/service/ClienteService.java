package com.techchallenge.core.applications.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import com.techchallenge.core.domain.Cliente;
import com.techchallenge.core.domain.exception.EntidadeEmUsoException;
import com.techchallenge.core.domain.exception.EntidadeNaoEncontradaException;
import com.techchallenge.core.domain.exception.NegocioException;
import com.techchallenge.drivers.db.entities.ClienteEntity;
import com.techchallenge.drivers.db.repositories.ClienteRepository;

//@Service
public class ClienteService {
	
	private static final String MSG_CLIENTE_EM_USO = "Clinte em uso com o id %d";
	private static final String MSG_CLIENTE_NAO_ENCONTRADO = "Cliente não encontrado com o id %d";
	private static final String MSG_CLIENTE_EXISTENTE = "Cliente com o CPF ou e-mail informados já cadastrado";

    @Autowired
    private ClienteRepository repository;

    @Transactional
    public ClienteEntity salvar(ClienteEntity cliente) {
    	List<ClienteEntity> clientes = repository.findByCpfOrEmail(cliente.getCpf(), cliente.getEmail());
    	
    	if (!clientes.isEmpty()) {
    		throw new NegocioException(MSG_CLIENTE_EXISTENTE);
    	}
    	
        return repository.save(cliente);
    }

    public List<ClienteEntity> buscarPorCpf(Long cpf) {
        return repository.findByCpfIs(cpf);
    }
    
    @Transactional
    public void excluir(Long clienteId) {
    	try {
    		repository.deleteById(clienteId);
    		repository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_CLIENTE_EM_USO, clienteId));
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format(MSG_CLIENTE_NAO_ENCONTRADO, clienteId));
		}
    }
    
    @Transactional
    public void atualizarDadosCliente(Long id, Cliente cliente) {
    	ClienteEntity clienteEntity = repository.findById(id)
    			.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(MSG_CLIENTE_NAO_ENCONTRADO, id)));
    	
    	clienteEntity.setEmail(cliente.getEmail());
    	clienteEntity.setNome(cliente.getNome());
    }
}
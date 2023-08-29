package com.techchallenge.adapter.gateways.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import com.techchallenge.adapter.gateways.ClienteGateway;
import com.techchallenge.adapter.mapper.business.ClienteBusinessMapper;
import com.techchallenge.adapter.mapper.db.ClienteEntityMapper;
import com.techchallenge.core.domain.entities.Cliente;
import com.techchallenge.core.domain.exception.EntidadeEmUsoException;
import com.techchallenge.core.domain.exception.EntidadeNaoEncontradaException;
import com.techchallenge.core.domain.exception.NegocioException;
import com.techchallenge.drivers.db.entities.ClienteEntity;
import com.techchallenge.drivers.db.repositories.ClienteRepository;

@Component
public class ClienteGatewayImpl implements ClienteGateway {
	
	private static final String MSG_CLIENTE_EM_USO = "Clinte em uso com o id %d";
	private static final String MSG_CLIENTE_NAO_ENCONTRADO = "Cliente não encontrado com o id %d";
	private static final String MSG_CLIENTE_EXISTENTE = "Cliente com o CPF ou e-mail informados já cadastrado";

    @Autowired
    private ClienteRepository repository;
    
    @Autowired
    private ClienteEntityMapper mapper;
    @Autowired
    private ClienteBusinessMapper businessMapper;

    @Transactional
    public Cliente salvar(Cliente cliente) throws NegocioException {
    	List<ClienteEntity> clientes = repository.findByCpfOrEmail(cliente.getCpf(), cliente.getEmail());
    	
    	ClienteEntity entity = mapper.toModel(cliente);
    	
    	if (!clientes.isEmpty()) {
    		throw new NegocioException(MSG_CLIENTE_EXISTENTE);
    		
    	}
    	
    	return businessMapper.toModel(repository.save(entity));
    }

    public List<Cliente> buscarPorCpf(Long cpf) {
        return businessMapper.toCollectionModel(repository.findByCpfIs(cpf));
    }
    
    @Transactional
    public void excluir(Long clienteId) throws NegocioException {
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
    public void atualizarDadosCliente(Long id, Cliente cliente) throws NegocioException {
    	ClienteEntity entity = repository.findById(id)
    			.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(MSG_CLIENTE_NAO_ENCONTRADO, id)));
    	
    	entity.setEmail(cliente.getEmail());
    	entity.setNome(cliente.getNome());
    }
}
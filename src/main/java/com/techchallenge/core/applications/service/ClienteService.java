package com.techchallenge.core.applications.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.techchallenge.core.applications.ports.ClienteRepository;
import com.techchallenge.core.domain.Cliente;
import com.techchallenge.core.domain.exception.EntidadeEmUsoException;
import com.techchallenge.core.domain.exception.EntidadeNaoEncontradaException;
import com.techchallenge.core.domain.exception.NegocioException;

@Service
public class ClienteService {
	
	private static final String MSG_CLIENTE_EM_USO = "Clinte em uso com o id %d";
	private static final String MSG_CLIENTE_NAO_ENCONTRADO = "Cliente não encontrado com o id %d";
	private static final String MSG_CLIENTE_EXISTENTE = "Cliente com o CPF informado já cadastrado";

    @Autowired
    private ClienteRepository repository;

    @Transactional
    public Cliente salvar(Cliente cliente) {
    	List<Cliente> clientes = repository.findByCpfIs(cliente.getCpf());
    	
    	if (!clientes.isEmpty()) {
    		throw new NegocioException(MSG_CLIENTE_EXISTENTE);
    	}
    	
        return repository.save(cliente);
    }

    public List<Cliente> buscarPorCpf(Long cpf) {
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
    	Cliente clienteEntity = repository.findById(id)
    			.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(MSG_CLIENTE_NAO_ENCONTRADO, id)));
    	
    	clienteEntity.setEmail(cliente.getEmail());
    	clienteEntity.setNome(cliente.getNome());
    }
}
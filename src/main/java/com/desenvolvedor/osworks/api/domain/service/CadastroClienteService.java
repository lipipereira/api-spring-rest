package com.desenvolvedor.osworks.api.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desenvolvedor.osworks.api.domain.exception.NegocioException;
import com.desenvolvedor.osworks.api.domain.model.Cliente;
import com.desenvolvedor.osworks.api.domain.repository.ClienteRepository;

/* Class para as regras de negocios da API  */

@Service // Para indica que essa classe e uma serviço da API
public class CadastroClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente salvar(Cliente cliente) {
		Cliente clienteExistente = clienteRepository.findByEmail(cliente.getEmail());
		
		// Verifica se o emial ja esta sendo usado por outro cliente
		if (clienteExistente != null && !clienteExistente.equals(cliente)) {
			throw new NegocioException("Ja existe esse email cadastrado com este e-mail");
		}
		
		return clienteRepository.save(cliente);	
	}
	
	public void excluir(Long clienteId) {
		clienteRepository.deleteById(clienteId);
	}
	
}

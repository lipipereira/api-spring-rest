package com.desenvolvedor.osworks.api.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.desenvolvedor.osworks.api.domain.model.Cliente;
import com.desenvolvedor.osworks.api.domain.repository.ClienteRepository;
import com.desenvolvedor.osworks.api.domain.service.CadastroClienteService;

	
@RestController
@RequestMapping("/clientes") // Controlado para responde tudo que esta em /clientes, para não ser necessario fica repetido no GetMapping o ("/cliente")
public class ClienteController {
	
//	@PersistenceContext
//	private EntityManager manager;
	
	@Autowired // Vai instancia(Vai ser vira) a Interface Clienterepository para esse class de controle
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CadastroClienteService cadastroCliente;
	
	@GetMapping
	public List<Cliente> Listar() {
		
		// Busca todos do banco de dados em lista
		return clienteRepository.findAll(); 
		
		// Busca por nome que contem o "ed" no nome
		//return clienteRepository.findByNomeContaining("ed"); 
		
		// Vai retorna a lista do Banco de dados
		//return manager.createQuery("from Cliente", Cliente.class).getResultList(); 
	}
	
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
		Optional<Cliente> cliente = clienteRepository.findById(clienteId);
		
		// Veuifica se existe esse CLiente no Banco e retorna um status
		if (cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get()); // Retorna o Status 200 - ok
		}
		return ResponseEntity.notFound().build(); // Retorna o Status 404 - not found
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED) // CREATED retorna o status 201 de criado 
	public Cliente adcionar(@Valid @RequestBody Cliente cliente) {
		return cadastroCliente.salvar(cliente);
	} 	
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long clienteId, 
			@RequestBody Cliente cliente){
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		cliente.setId(clienteId);
		cliente = cadastroCliente.salvar(cliente);
			
		return ResponseEntity.ok(cliente);
	}
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> remover (@PathVariable Long clienteId){
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		cadastroCliente.excluir(clienteId);
		
		return ResponseEntity.noContent().build();
	}
	
	
}	

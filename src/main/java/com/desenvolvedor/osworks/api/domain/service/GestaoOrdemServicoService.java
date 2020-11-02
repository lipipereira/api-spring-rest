package com.desenvolvedor.osworks.api.domain.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desenvolvedor.osworks.api.domain.exception.NegocioException;
import com.desenvolvedor.osworks.api.domain.model.Cliente;
import com.desenvolvedor.osworks.api.domain.model.OrdemServico;
import com.desenvolvedor.osworks.api.domain.model.StatusOrdemServico;
import com.desenvolvedor.osworks.api.domain.repository.ClienteRepository;
import com.desenvolvedor.osworks.api.domain.repository.OrdemServicoRepository;

@Service
public class GestaoOrdemServicoService {

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;

	@Autowired
	private ClienteRepository clienteRepository;
	
	public OrdemServico Criar(OrdemServico ordemServico) {
		Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
				.orElseThrow(()->new NegocioException("Cliente não encontrado"));		
		
		ordemServico.setCliente(cliente);
		ordemServico.setStatus(StatusOrdemServico.ABERTA);
		ordemServico.setDataAbertura(LocalDateTime.now());
		
		return ordemServicoRepository.save(ordemServico);
	}
	
}

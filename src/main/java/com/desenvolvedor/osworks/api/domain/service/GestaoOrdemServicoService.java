package com.desenvolvedor.osworks.api.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desenvolvedor.osworks.api.domain.exception.EntidadeNaoEncontradaException;
import com.desenvolvedor.osworks.api.domain.exception.NegocioException;
import com.desenvolvedor.osworks.api.domain.model.Cliente;
import com.desenvolvedor.osworks.api.domain.model.OrdemServico;
import com.desenvolvedor.osworks.api.domain.model.StatusOrdemServico;
import com.desenvolvedor.osworks.api.domain.repository.ClienteRepository;
import com.desenvolvedor.osworks.api.domain.repository.ComentarioRepository;
import com.desenvolvedor.osworks.api.domain.repository.OrdemServicoRepository;
import com.desenvolvedor.osworks.api.model.Comentario;

@Service
public class GestaoOrdemServicoService {

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ComentarioRepository comentarioRepository;
	
	// Inicia uma ordem de serviço 
	public OrdemServico Criar(OrdemServico ordemServico) {
		// Verifica se existe um Cliente com o código informado	
		Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
				.orElseThrow(() -> new NegocioException(" Cliente não encontrado "));		
		
		// Passa os paramentros da ordem de serviço dados padrão
		ordemServico.setCliente(cliente);
		ordemServico.setStatus(StatusOrdemServico.ABERTA);
		ordemServico.setDataAbertura(OffsetDateTime.now());
		
		return ordemServicoRepository.save(ordemServico);
	}
	
	public Comentario adicionarComentario(Long OrdemServicoId, String descricao) {
		OrdemServico ordemServico = ordemServicoRepository.findById(OrdemServicoId)
				.orElseThrow(()-> new EntidadeNaoEncontradaException("Ordem serviço nã encontrada"));
		
		Comentario comentario = new Comentario();
		comentario.setDataEnvio(OffsetDateTime.now());
		comentario.setDescricao(descricao);
		comentario.setOrdemServico(ordemServico);
		
		return comentarioRepository.save(comentario);
	}
}

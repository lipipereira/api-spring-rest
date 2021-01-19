package com.desenvolvedor.osworks.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desenvolvedor.osworks.api.domain.exception.EntidadeNaoEncontradaException;
import com.desenvolvedor.osworks.api.domain.model.OrdemServico;
import com.desenvolvedor.osworks.api.domain.repository.OrdemServicoRepository;
import com.desenvolvedor.osworks.api.domain.service.GestaoOrdemServicoService;
import com.desenvolvedor.osworks.api.model.Comentario;
import com.desenvolvedor.osworks.api.model.ComentarioInput;
import com.desenvolvedor.osworks.api.model.ComentarioModel;

@RestController
@RequestMapping("/ordens-servico/{ordemServicoId}/comentarios")
public class ComentarioController {

	@Autowired
	private GestaoOrdemServicoService gestaoOrdemServicoService;
	
	private OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	private ModelMapper modelMapper; 
		
	@GetMapping 
	public List<ComentarioModel>listar(@PathVariable Long ordemServicoId){
		OrdemServico  ordemservico = ordemServicoRepository.findById(ordemServicoId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de serviço não encontrada"));
		
		return toColletionModel(ordemservico.getComentarios());
	}
	
	public ComentarioModel adcionar(@PathVariable Long ordemServicoId, 
			@Valid @RequestBody ComentarioInput comentarioInput) {
		
		Comentario comentario = gestaoOrdemServicoService.adicionarComentario(ordemServicoId, comentarioInput.getDescricao());
		
		return toModel(comentario);
	}
	
	private ComentarioModel toModel(Comentario comentario) {
		return modelMapper.map(comentario, ComentarioModel.class);
	}
	
	private List<ComentarioModel> toColletionModel(List<Comentario> comentarios){
		return comentarios.stream()
				.map(comentario -> toModel(comentario))
				.collect(Collectors.toList());
	}
}

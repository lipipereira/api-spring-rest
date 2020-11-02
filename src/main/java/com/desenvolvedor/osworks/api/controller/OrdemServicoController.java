package com.desenvolvedor.osworks.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.desenvolvedor.osworks.api.domain.model.OrdemServico;
import com.desenvolvedor.osworks.api.domain.repository.OrdemServicoRepository;
import com.desenvolvedor.osworks.api.domain.service.GestaoOrdemServicoService;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {
	
	@Autowired
	private GestaoOrdemServicoService gestaoOrdemServicoService;
	
	
	@Autowired
	private OrdemServicoRepository ordem;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrdemServico Criar (@Valid @RequestBody OrdemServico ordemServico ) {
		return gestaoOrdemServicoService.Criar(ordemServico);
		
	}
	
	@GetMapping
	public List<OrdemServico> List(){
		return ordem.findAll();
	}
}

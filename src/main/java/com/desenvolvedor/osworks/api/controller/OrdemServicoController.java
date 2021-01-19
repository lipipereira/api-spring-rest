package com.desenvolvedor.osworks.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.desenvolvedor.osworks.api.domain.model.OrdemServico;
import com.desenvolvedor.osworks.api.domain.repository.OrdemServicoRepository;
import com.desenvolvedor.osworks.api.domain.service.GestaoOrdemServicoService;
import com.desenvolvedor.osworks.api.model.OrdemServiceInput;
import com.desenvolvedor.osworks.api.model.OrdemServiceModel;	
	


@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {
	
	@Autowired
	private GestaoOrdemServicoService gestaoOrdemServicoService;
	
	@Autowired
	private OrdemServicoRepository ordem;
	
	// Como é um codigo que não é gerenciado pelo String e sim de terceiros , temos que criar uma Classe
	@Autowired
	private ModelMapper modelMapper;
	
	// Anotação @Valid serve para valida  os paramentros passados, de acordo com a anotação da Classe Model passada	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrdemServiceModel Criar (@Valid @RequestBody OrdemServiceInput ordemServicoInput ) {
		OrdemServico ordemServico = toEntity(ordemServicoInput); 
		return toModel( gestaoOrdemServicoService.Criar(ordemServico) );
	}
	
	@GetMapping
	public List<OrdemServiceModel> List(){
		return toCollectionModel(ordem.findAll() );	
	}
	
	// Anotação @PathVaiable, serve para usa variave do link(caminho)
	@GetMapping("/{ordemServicoID}")
	public ResponseEntity<OrdemServiceModel> buscar( @PathVariable Long ordemServicoID ) {
		Optional<OrdemServico> ordemServico =  ordem.findById(ordemServicoID);
		
		// Verifica se tem alguma retorno	
		if (ordemServico.isPresent()) {	
			// Pode ir colocando o que retorna na resposnta da buscar usando o OrdemServiceModel
			// mas para envita fica repetido esse mesmo codigo para todos vamos usa uma biblipteca  modelmappper	
		/* OrdemServiceModel model = new OrdemServiceModel();
			model.setID(ordemServico.get().getId()); 
			model.setDescri(ordemServico.get().getDescricao());
			// . . . */
			// Usando o Modelmapper vai pega o contedudo do ordemService(Optional) e passar para o OrdemServicoModel
			OrdemServiceModel model = toModel(ordemServico.get());
			return ResponseEntity.ok(model);
		}
		// Retorna vazio caso seja vazio a ordem de serviço
		return ResponseEntity.notFound().build();
	}
	
	private OrdemServiceModel toModel( OrdemServico ordemServico ) {
		return modelMapper.map(ordemServico, OrdemServiceModel.class);
	}
	
	// .stream() serve para retorno um fluxo de elemetos 
	private List<OrdemServiceModel> toCollectionModel(List<OrdemServico> ordensServico){
		// Faz a conveção de todos os eletemos da lsta para a OrdemServiceModel
		return ordensServico.stream().map(ordem-> toModel(ordem) ).collect(Collectors.toList());  				
	}
	
	private OrdemServico toEntity( OrdemServiceInput ordemServiceInput ) {
		return modelMapper.map(ordemServiceInput, OrdemServico.class);
	}
}
	
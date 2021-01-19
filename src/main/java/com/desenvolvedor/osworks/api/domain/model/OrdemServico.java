package com.desenvolvedor.osworks.api.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.desenvolvedor.osworks.api.domain.ValidationGroups;
import com.desenvolvedor.osworks.api.model.Comentario;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;


@Entity
public class OrdemServico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// Ja esta validando o na api e no domain, se esta validado pela API não precisa duplica a validação
	// Se não pode deixa as validações no dois lugares
	//@Valid 
	// Para ao validar Cliente ele validar somente as anotações que usar essa ValidationGrupos no caso somente o ID do Cliente
	//@ConvertGroup(from = Default.class, to = ValidationGroups.ClienteId.class) 
	//@NotNull
	@ManyToOne // Muitos para um. Relacionamente de tabelas (Muitas ordem serviço para um)
	//@JoinColumn(name = "id_cliente")  - Especifica a coluna para uma forengKey
	private Cliente cliente;
	
	//@NotBlank
	private String descricao;
	
	//@NotNull
	private BigDecimal preco;
	
	// Anotação para pertimir somente leitura pelo cliente
	//@JsonProperty(access = Access.READ_ONLY) 	
	@Enumerated(EnumType.STRING) // Especifica o que vai ser salvo no banco do ENUM
	private StatusOrdemServico 	status;
	
	// Anotação para pertimir somente leitura pelo cliente
	//@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime dataAbertura;
	
	// Anotação para pertimir somente leitura pelo cliente
	//@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime dataFinalizacao;
	
	// Mapeamnete para uma ordem serviço pode ter varios comentarios
	@OneToMany(mappedBy = "ordemServico")
	private List<Comentario> comentarios = new ArrayList<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	public StatusOrdemServico getStatus() {
		return status;
	}
	public void setStatus(StatusOrdemServico status) {
		this.status = status;
	}
	public OffsetDateTime getDataAbertura() {
		return dataAbertura;
	}
	public void setDataAbertura(OffsetDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	public OffsetDateTime getDataFinalizacao() {
		return dataFinalizacao;
	}
	public void setDataFinalizacao(OffsetDateTime dataFinalizacao) {
		this.dataFinalizacao = dataFinalizacao;
	}
	public List<Comentario> getComentarios() {
		return comentarios;
	}
	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrdemServico other = (OrdemServico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}

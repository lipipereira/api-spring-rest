package com.desenvolvedor.osworks.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.desenvolvedor.osworks.api.domain.model.StatusOrdemServico;

public class OrdemServiceModel {
	
	private Long ID;
	// Trazer o nome Model do Cliente ma consulta da ordem Service
	private ClienteResumeModel cliente;
	private String Descricao;
	private BigDecimal preco;
	private StatusOrdemServico status;
	private OffsetDateTime dataAbertura;
	private OffsetDateTime dataFinalizacao;
	
	
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public ClienteResumeModel getCliente() {
		return cliente;
	}
	public void setCliente(ClienteResumeModel cliente) {
		this.cliente = cliente;
	}
	public String getDescricao() {
		return Descricao;
	}
	public void setDescricao(String Descricao) {
		this.Descricao = Descricao;
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
	
	
}

package com.sicredi.desafio.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Sessao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInicio;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataFim;
	@OneToOne(optional = false)
	private Pauta pauta;
	@JsonIgnore
	private Boolean enviadoParaTopico; 
	
	public Sessao() {
		
	}
	
	public Sessao(Date dataInicio, Date dataFim, Pauta pauta, Boolean enviadoParaTopico ) {
		super();
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.pauta = pauta;
		this.enviadoParaTopico = enviadoParaTopico;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataFim() {
		return dataFim;
	}
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	public Pauta getPauta() {
		return pauta;
	}
	public void setPauta(Pauta pauta) {
		this.pauta = pauta;
	}

	public Boolean getEnviadoParaTopico() {
		return enviadoParaTopico;
	}

	public void setEnviadoParaTopico(Boolean enviadoParaTopico) {
		this.enviadoParaTopico = enviadoParaTopico;
	}
	
}

package com.sicredi.desafio.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonAlias;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Voto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(hidden = true) 
    private Long id;
	@OneToOne()
	@JsonAlias("pautaId")
	private Pauta pauta;
	private String escolha;
	private String idAssociado;
	
	public Voto() {
		
	}
	
	public Voto(Pauta pauta, String escolha, String idAssociado) {
		super();
		this.pauta = pauta;
		this.escolha = escolha;
		this.idAssociado = idAssociado;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Pauta getPauta() {
		return pauta;
	}
	public void setPauta(Pauta pauta) {
		this.pauta = pauta;
	}
	public String getEscolha() {
		return escolha;
	}
	public void setEscolha(String escolha) {
		this.escolha = escolha;
	}
	public String getIdAssociado() {
		return idAssociado;
	}
	public void setIdAssociado(String idAssociado) {
		this.idAssociado = idAssociado;
	}
	
	
}

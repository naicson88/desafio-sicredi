package com.sicredi.desafio.data.dto;

import java.io.Serializable;

public class ResultadoVotacaoPautaDto  implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long pautaId;
	private Long votosSim;
	private Long votosNao;
	private Integer totalDeVotos;
	
	public Long getPautaId() {
		return pautaId;
	}
	public void setPauta(Long pautaId) {
		this.pautaId = pautaId;
	}
	public Long getVotosSim() {
		return votosSim;
	}
	public void setVotosSim(Long votosSim) {
		this.votosSim = votosSim;
	}
	public Long getVotosNao() {
		return votosNao;
	}
	public void setVotosNao(Long votosNao) {
		this.votosNao = votosNao;
	}
	public Integer getTotalDeVotos() {
		return totalDeVotos;
	}
	public void setTotalDeVotos(Integer totalDeVotos) {
		this.totalDeVotos = totalDeVotos;
	}
	
}

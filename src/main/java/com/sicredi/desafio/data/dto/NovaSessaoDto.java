package com.sicredi.desafio.data.dto;

public class NovaSessaoDto {
	
	private Long pautaId;
	private Integer  tempoSessaoAtivaEmMinutos;
	
	public Long getPautaId() {
		return pautaId;
	}
	public void setPautaId(Long pautaId) {
		this.pautaId = pautaId;
	}
	public Integer getTempoSessaoAtivaEmMinutos() {
		return tempoSessaoAtivaEmMinutos;
	}
	public void setTempoSessaoAtivaEmMinutos(Integer tempoSessaoAtivaEmMinutos) {
		this.tempoSessaoAtivaEmMinutos = tempoSessaoAtivaEmMinutos;
	}
	
	
}

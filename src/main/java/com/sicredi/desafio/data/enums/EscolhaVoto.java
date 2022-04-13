package com.sicredi.desafio.data.enums;

public enum EscolhaVoto {
	
	SIM("SIM"),
	NÃO("NÃO");
	
	private final String escolha;
	
	EscolhaVoto(String voto){
		this.escolha = voto;
	}
	
	public String getEscolha() {
		return escolha;
	}
	
    public static boolean validar(String voto) {

    	    for (EscolhaVoto v : EscolhaVoto.values()) {
    	        if (v.name().equals(voto)) {
    	            return true;
    	        }
    	    }
    	    
    	    return false;
    	
    }
}

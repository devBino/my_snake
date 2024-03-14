package br.com.mysnake.repo.type;

import java.util.Arrays;
import java.util.Optional;

public enum TipoComando {

	CIMA(38),
	BAIXO(40),
	ESQUERDA(37),
	DIREITA(39);
	
	int code;
	
	TipoComando(int pCode){
		code = pCode;
	}
	
	public int getCode() {
		return code;
	}
	
	public static TipoComando fromValue(int pCode) {
		
		final Optional<TipoComando> comandoCandidato = Arrays
				.stream(TipoComando.values())
				.filter(tp -> tp.getCode() == pCode)
				.findFirst();
		
		if( comandoCandidato.isPresent() ) {
			return comandoCandidato.get();
		}
		
		return null;
		
	}
	
}

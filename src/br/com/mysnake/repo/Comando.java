package br.com.mysnake.repo;

import br.com.mysnake.repo.type.TipoComando;

public class Comando {

	private boolean processado;
	private TipoComando tipoComando;
	
	public Comando(final TipoComando pTipocComando) {
		tipoComando = pTipocComando;
	}

	public synchronized boolean isProcessado() {
		return processado;
	}

	public synchronized void setProcessado(boolean processado) {
		this.processado = processado;
		notify();
	}
	
	public void setTipoComando(TipoComando tipoComando) {
		this.tipoComando = tipoComando;
	}
	
	public TipoComando getTipoComando() {
		return tipoComando;
	}
	
}

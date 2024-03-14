package br.com.mysnake.repo;

import br.com.mysnake.repo.type.TipoComando;

public class PosicaoSnakeCalculator {

	private MemoryGame memory;
	
	public PosicaoSnakeCalculator() {
		memory = MemoryGame.getInstance();
	}
	
	public void realizarCalculos() {
		
		Comando cmd = memory.getComandoEmProcessamento();
		TipoComando tipoComando = cmd.getTipoComando();
		
		if( !validarMovimentos(tipoComando) ) {
			return;
		}
		
		memory.setCurrentDirection( tipoComando.getCode() );
		
		deslocarSnake();
		
		memory.setLastDirection( memory.getCurrentDirection() );
		
	}
	
	public void deslocarSnake() {
		
		int x = memory.getSnakeX();
		int y = memory.getSnakeY();
		
		if( TipoComando.CIMA.getCode() == memory.getCurrentDirection() ) {
			
			memory.setSnakeX(--x);
			
		}else if( TipoComando.BAIXO.getCode() == memory.getCurrentDirection() ) {
			
			memory.setSnakeX(++x);
			
		}else if( TipoComando.ESQUERDA.getCode() == memory.getCurrentDirection() ) {
			
			memory.setSnakeY(--y);
			
		}else if( TipoComando.DIREITA.getCode() == memory.getCurrentDirection() ) {
			
			memory.setSnakeY(++y);
			
		}
		
	}
	
	private boolean validarMovimentos(final TipoComando pTipoComandoAtual) {
		
		if( pTipoComandoAtual.getCode() == memory.getLastDirection() ) {
			return false;
		}
		
		if( !validaMovimentoParaBaixo(pTipoComandoAtual) ) {
			return false;
		}
		
		if( !validaMovimentoParaCima(pTipoComandoAtual) ) {
			return false;
		}
		
		if( !validaMovimentoParaEsquerda(pTipoComandoAtual) ) {
			return false;
		}
		
		if( !validaMovimentoParaDireita(pTipoComandoAtual) ) {
			return false;
		}
		
		return true;
		
	}
	
	private boolean validaMovimentoParaBaixo(final TipoComando pTipoComandoAtual) {
		
		TipoComando cmdAnterior = TipoComando.fromValue( memory.getLastDirection() );
		
		if( cmdAnterior != null && TipoComando.CIMA.getCode() == cmdAnterior.getCode()
				&& TipoComando.BAIXO.getCode() == pTipoComandoAtual.getCode() ) {
			return false;
		}
		
		return true;
		
	}
	
	private boolean validaMovimentoParaCima(final TipoComando pTipoComandoAtual) {
		
		TipoComando cmdAnterior = TipoComando.fromValue( memory.getLastDirection() );
		
		if( cmdAnterior != null && TipoComando.BAIXO.getCode() == cmdAnterior.getCode()
				&& TipoComando.CIMA.getCode() == pTipoComandoAtual.getCode() ) {
			return false;
		}
		
		return true;
		
	}
	
	private boolean validaMovimentoParaEsquerda(final TipoComando pTipoComandoAtual) {
		
		TipoComando cmdAnterior = TipoComando.fromValue( memory.getLastDirection() );
		
		if( cmdAnterior != null && TipoComando.DIREITA.getCode() == cmdAnterior.getCode()
				&& TipoComando.ESQUERDA.getCode() == pTipoComandoAtual.getCode() ) {
			return false;
		}
		
		return true;
		
	}
	
	private boolean validaMovimentoParaDireita(final TipoComando pTipoComandoAtual) {
		
		TipoComando cmdAnterior = TipoComando.fromValue( memory.getLastDirection() );
		
		if( cmdAnterior != null && TipoComando.ESQUERDA.getCode() == cmdAnterior.getCode()
				&& TipoComando.DIREITA.getCode() == pTipoComandoAtual.getCode() ) {
			return false;
		}
		
		return true;
		
	}
	
}

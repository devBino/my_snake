package br.com.mysnake.repo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.mysnake.repo.type.TipoComando;

public class MemoryGame {

	private static MemoryGame instance;
	
	private boolean end;
	private int lastDirection, currentDirection;
	private Comando comandoEmProcessamento;
	private int qtdeMordidas;
	private int snakeX, snakeY;
	private List<Comando> filaComandos;
	
	private MemoryGame() {
	
		filaComandos = new ArrayList<>();
		
		snakeX = -1;
		snakeY = -1;
		lastDirection = -1;
		currentDirection = -1;
		
	}
	
	public synchronized static MemoryGame getInstance() {
		
		if( instance == null ) {
			instance = new MemoryGame();
		}
		
		return instance;
		
	}
	
	public synchronized boolean isEnd() {
		return end;
	}
	
	public synchronized void setEnd() {
		end = true;
		notifyAll();
	}
	
	public synchronized void setLastDirection(int lastDirection) {
		this.lastDirection = lastDirection;
		notify();
	}
	
	public synchronized int getLastDirection() {
		return lastDirection;
	}
	
	public synchronized void setCurrentDirection(int currentDirection) {
		this.currentDirection = currentDirection;
		notify();
	}
	
	public synchronized int getCurrentDirection() {
		return currentDirection;
	}
	
	public synchronized boolean changedDireciton() {
		return lastDirection != currentDirection;
	}
	
	public synchronized void incrementMordida() {
		qtdeMordidas++;
		notify();
	}
	
	public synchronized int getQtdeMordidas() {
		return qtdeMordidas;
	}
	
	public synchronized int getSnakeX() {
		return snakeX;
	}
	
	public synchronized void setSnakeX(int snakeX) {
		this.snakeX = snakeX;
		notify();
	}
	
	public synchronized int getSnakeY() {
		return snakeY;
	}
	
	public synchronized void setSnakeY(int snakeY) {
		this.snakeY = snakeY;
		notify();
	}

	public synchronized List<Comando> getFilaComandos() {
		return filaComandos;
	}
	
	public synchronized void criarComando(final int pCode) {
		
		final TipoComando tipoComando = TipoComando.fromValue(pCode);
		
		if( tipoComando == null ) {
			return;
		}
		
		final Comando comando = new Comando(tipoComando);
		
		filaComandos.add(comando);
		
		notify();
		
	}
	
	public synchronized Comando getProximoComandoPendente() {
		
		final Optional<Comando> candidatoComando = getFilaComandos()
				.stream()
				.filter(cmd -> !cmd.isProcessado())
				.findFirst();
		
		if( candidatoComando.isPresent() ) {
			return candidatoComando.get();
		}
		
		return null;
		
	}

	public synchronized Comando getComandoEmProcessamento() {
		return comandoEmProcessamento;
	}

	public synchronized void setComandoEmProcessamento(Comando comandoEmProcessamento) {
		this.comandoEmProcessamento = comandoEmProcessamento;
		notify();
	}
	
	
	
}

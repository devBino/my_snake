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
	private int ratosPegos;
	private int snakeX, snakeY;
	private List<Comando> filaComandos;
	private int ratoX;
	private int ratoY;
	private boolean ratoVivo;
	private List<Coordenada> historicoCoordenadasRato;
	
	private MemoryGame() {
	
		filaComandos = new ArrayList<>();
		historicoCoordenadasRato = new ArrayList<>();
		
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
	
	public synchronized void end() {
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
	
	public synchronized void incrementRatosPegos() {
		ratosPegos++;
		notify();
	}
	
	public synchronized int getRatosPegos () {
		return ratosPegos;
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

	public synchronized int getRatoX() {
		return ratoX;
	}

	public synchronized void setRatoX(int ratoX) {
		this.ratoX = ratoX;
		notify();
	}

	public synchronized int getRatoY() {
		return ratoY;
	}

	public synchronized void setRatoY(int ratoY) {
		this.ratoY = ratoY;
		notify();
	}

	public synchronized boolean isRatoVivo() {
		return ratoVivo;
	}

	public synchronized void setRatoVivo(boolean ratoVivo) {
		this.ratoVivo = ratoVivo;
		notify();
	}
	
	public synchronized List<Coordenada> getHistoricoCoordenadasRato() {
		return historicoCoordenadasRato;
	}
	
	public synchronized void criarCoordenadaRato(int x, int y) {
		ratoX = x;
		ratoY = y;
		historicoCoordenadasRato.add(new Coordenada(x, y));
		notify();
	}
	
	public synchronized Coordenada getCoordenadaRatoAtiva() {
		
		final Optional<Coordenada> candidato = historicoCoordenadasRato
				.stream()
				.filter(c -> c.isAtiva())
				.findFirst();
		
		if( candidato.isPresent() ) {
			return candidato.get();
		}
		
		return null;
		
	}
	
	public synchronized boolean snakeAlreadLunch() {
		
		if( snakeX == ratoX && snakeY == ratoY ) {
			return true;
		}
		
		return false;
	}
	
	public synchronized boolean bateu() {
		
		//monitora impacto cima baixo
		if( snakeX < 0 || snakeX > GameConstants.DX ) {
			return true;
		}
		
		//monitora impacto esquerda direita
		if( snakeY < 0 || snakeY > GameConstants.DY ) {
			return true;
		}
		
		return false;
		
	}
	
}

package br.com.mysnake.processo;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import br.com.mysnake.repo.GameConstants;
import br.com.mysnake.repo.MemoryGame;

public class RatoCreate {

	private Thread processo;
	
	public RatoCreate() {
		processo = new Thread(new TaskRatoCreate());
		processo.start();
	}
	
}

class TaskRatoCreate implements Runnable {
	
	MemoryGame memory;
	
	public TaskRatoCreate() {
		memory = MemoryGame.getInstance();
	}
	
	@Override
	public void run() {
		while(!memory.isEnd()) {
			esperarRatoMorrer();
		}
	}
	
	private void esperarRatoMorrer() {
		
		while( memory.getCoordenadaRatoAtiva() != null ) {
			try {
				TimeUnit.MICROSECONDS.sleep(200);
				wait();
			}catch(final Exception exception) {}
		}
		
		criarDadosNovoRato();
		
	}
	
	private void criarDadosNovoRato() {
		
		Random rd = new Random();
		
		int rdX = rd.nextInt(0,GameConstants.DX);
		int rdY = rd.nextInt(0, GameConstants.DY);
		
		if( rdX == memory.getSnakeX() ) {
			criarDadosNovoRato();
			return;
		}
		
		if( rdY == memory.getSnakeY() ) {
			criarDadosNovoRato();
			return;
		}
		
		memory.criarCoordenadaRato(rdX, rdY);
		
	}
	
}
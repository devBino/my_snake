package br.com.mysnake.processo;

import java.util.concurrent.TimeUnit;

import br.com.mysnake.app.Game;
import br.com.mysnake.repo.Matriz;
import br.com.mysnake.repo.MemoryGame;

public class DesenharGrafico {
	
	public Thread processo;
	
	public DesenharGrafico(final Game game) {
		processo = new Thread(new TaskDesenho(game));
		processo.start();
	}
	
}

class TaskDesenho implements Runnable {

	Game game;
	MemoryGame memory;
	
	public TaskDesenho(final Game game) {
		this.game = game;
		memory = MemoryGame.getInstance();
	}
	
	@Override
	public void run() {
		aplicarGrafico();
	}
	
	private void aplicarGrafico() {
		
		while( !memory.isEnd() ) {
			try {
				
				final StringBuilder fundo = Matriz.getMatrizInicial(40, 70);
				game.getTxtFundo().setText(fundo.toString());
				
				TimeUnit.MILLISECONDS.sleep(500);
				
				wait();
				
			}catch(Exception e) {}
		}
		
	}
	
}

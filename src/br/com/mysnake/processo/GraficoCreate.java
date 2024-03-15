package br.com.mysnake.processo;

import java.util.concurrent.TimeUnit;

import br.com.mysnake.app.GameView;
import br.com.mysnake.repo.GameConstants;
import br.com.mysnake.repo.Matriz;
import br.com.mysnake.repo.MemoryGame;

public class GraficoCreate {
	
	public Thread processo;
	
	public GraficoCreate(final GameView game) {
		processo = new Thread(new TaskGraficoCreate(game));
		processo.start();
	}
	
}

class TaskGraficoCreate implements Runnable {

	GameView game;
	MemoryGame memory;
	
	public TaskGraficoCreate(final GameView game) {
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
				
				final StringBuilder fundo = Matriz.getMatrizGrafico(
						GameConstants.DX, GameConstants.DY);

				game.getTxtFundo().setText(fundo.toString());
				
				TimeUnit.MILLISECONDS.sleep(300);
				
				wait();
				
			}catch(Exception e) {}
		}
		
		game.getTxtFundo().setText("GAME OVER...");
		
	}
	
}

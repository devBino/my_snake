package br.com.mysnake.processo;

import java.util.concurrent.TimeUnit;

import br.com.mysnake.repo.Coordenada;
import br.com.mysnake.repo.MemoryGame;

public class SnakeLunchMonitor {

	private Thread processo;
	
	public SnakeLunchMonitor() {
		processo = new Thread(new TaskSnakeLunchMonitor());
		processo.start();
	}
	
}

class TaskSnakeLunchMonitor implements Runnable {
	
	MemoryGame memory;
	
	public TaskSnakeLunchMonitor() {
		memory = MemoryGame.getInstance();
	}
	
	@Override
	public void run() {
		while( !memory.isEnd() ) {
			aguardarSnakeLanchar();
		}
	}
	
	private void aguardarSnakeLanchar() {
		
		while( !memory.snakeAlreadLunch() ) {
			try {
				TimeUnit.MILLISECONDS.sleep(250);
				wait();
			}catch(final Exception exception) {}
		}
		
		final Coordenada coordenada = memory.getCoordenadaRatoAtiva();
		
		if( coordenada != null ) {
			coordenada.desativar();
			memory.incrementRatosPegos();
		}
		
	}
	
}

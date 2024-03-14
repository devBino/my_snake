package br.com.mysnake.processo;

import br.com.mysnake.repo.MemoryGame;
import br.com.mysnake.repo.PosicaoSnakeCalculator;

public class ComandoProcessor {

	private Thread processo;
	
	public ComandoProcessor() {
		processo = new Thread(new TaskComandoProcessor());
		processo.start();
	}
	
}

class TaskComandoProcessor implements Runnable {
	
	private MemoryGame memory;

	public TaskComandoProcessor() {
		memory = MemoryGame.getInstance();
	}
	
	@Override
	public void run() {
		while( !memory.isEnd() ) {
			aguardarComando();
		}
	}
	
	private void aguardarComando() {
		
		while( memory.getComandoEmProcessamento() == null ) {
			try {
				wait();
			}catch(final Exception exception) {}
		}
		
		processarComando();
		
	}
	
	private void processarComando() {
		
		PosicaoSnakeCalculator calculator = new PosicaoSnakeCalculator();
		
		calculator.realizarCalculos();
		
		memory.getComandoEmProcessamento().setProcessado(true);
		memory.setComandoEmProcessamento(null);
		
	}
	
}

package br.com.mysnake.processo;

import java.util.concurrent.TimeUnit;

import br.com.mysnake.repo.Comando;
import br.com.mysnake.repo.MemoryGame;

public class MonitorComando {

	private Thread processo;
	
	public MonitorComando() {
		processo = new Thread(new TaskMonitor());
		processo.start();
	}
	
}

class TaskMonitor implements Runnable {
	
	MemoryGame memory;
	
	public TaskMonitor() {
		memory = MemoryGame.getInstance();
	}
	
	@Override
	public void run() {
		monitorarComandos();
	}
	
	private void monitorarComandos() {
		while( !memory.isEnd() ) {
			try {
				
				final Comando cmd = memory.getProximoComandoPendente();
				
				if( cmd != null && memory.getComandoEmProcessamento() == null ) {
					memory.setComandoEmProcessamento(cmd);
				}
				
				TimeUnit.MILLISECONDS.sleep(250);
				wait();
				
			}catch(final Exception exception) {}
		}
	}
	
}

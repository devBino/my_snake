package br.com.mysnake.processo;

import java.util.concurrent.TimeUnit;

import br.com.mysnake.repo.MemoryGame;

public class ImpactoMonitor {

	private Thread processo;
	
	public ImpactoMonitor() {
		processo = new Thread(new TaskImpactoMonitor());
		processo.start();
	}
	
}

class TaskImpactoMonitor implements Runnable {
	
	private MemoryGame memory;
	
	public TaskImpactoMonitor() {
		memory = MemoryGame.getInstance();
	}
	
	@Override
	public void run() {
		
		while(!memory.bateu()) {
			try {
				TimeUnit.MILLISECONDS.sleep(100);
				wait();
			}catch(final Exception exception) {}
		}
		
		memory.end();
		
	}
	
}
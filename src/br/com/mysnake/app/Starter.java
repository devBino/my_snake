package br.com.mysnake.app;

import br.com.mysnake.processo.ComandoProcessor;
import br.com.mysnake.processo.DesenharGrafico;
import br.com.mysnake.processo.MonitorComando;

public class Starter {
	
	public static void main(String[] args) {
		
		Game game = new Game();
		game.setVisible(true);
		
		DesenharGrafico d = new DesenharGrafico(game);
		MonitorComando m = new MonitorComando();
		ComandoProcessor c = new ComandoProcessor();
		
	}
	
}

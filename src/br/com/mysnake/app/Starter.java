package br.com.mysnake.app;

import br.com.mysnake.processo.ComandoProcessor;
import br.com.mysnake.processo.GraficoCreate;
import br.com.mysnake.processo.ImpactoMonitor;
import br.com.mysnake.processo.ComandoMonitor;
import br.com.mysnake.processo.RatoCreate;
import br.com.mysnake.processo.SnakeLunchMonitor;

public class Starter {
	
	public static void main(String[] args) {
		
		GameView game = new GameView();
		game.setVisible(true);
		
		new GraficoCreate(game);
		new ComandoMonitor();
		new ComandoProcessor();
		new RatoCreate();
		new SnakeLunchMonitor();
		new ImpactoMonitor();
		
	}
	
}

package br.com.mysnake.repo;

import java.util.Random;

public class Matriz {

	static String unidade = " ";
	
	private static int snakeSize() {
		MemoryGame memory = MemoryGame.getInstance();
		int size = memory.getQtdeMordidas() + 1;
		return size;
	}
	
	public static StringBuilder getMatrizInicial(int x, int y) {
		
		final StringBuilder mt = new StringBuilder();
		
		MemoryGame memory = MemoryGame.getInstance();
		
		int mx = x / 2;
		int my = y / 2;
		
		if( memory.getSnakeX() == -1 ) {
			memory.setSnakeX(mx);
		}
		
		if( memory.getSnakeY() == -1 ) {
			memory.setSnakeY(my);
		}
		
		if( memory.getComandoEmProcessamento() == null ) {
			
			if( memory.getLastDirection() != -1 ) {
				memory.setCurrentDirection( memory.getLastDirection() );
			}
			
			PosicaoSnakeCalculator calculator = new PosicaoSnakeCalculator();
			calculator.deslocarSnake();
			
		}
		
		for(int i=0; i<x; i++) {
			
			for(int j=0; j<y; j++) {
				if( i == memory.getSnakeX() && j == memory.getSnakeY() ) {
					mt.append("#");
				}else {
					mt.append(unidade);
				}
			}
			
			mt.append("\n");
			
		}
		
		return mt;
		
	}
	
}

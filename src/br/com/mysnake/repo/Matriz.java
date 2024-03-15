package br.com.mysnake.repo;

public class Matriz {

	static String unidade;
	static String snake;
	static String rato;
	
	static{
		unidade = " ";
		snake = "\u26AA";
		rato = "\uD83D\uDC2D";
	}
	
	private static int snakeSize() {
		MemoryGame memory = MemoryGame.getInstance();
		int size = memory.getRatosPegos() + 1;
		return size;
	}
	
	public static StringBuilder getMatrizGrafico(int x, int y) {
		
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
		
		final Coordenada ratoAtivo = memory.getCoordenadaRatoAtiva();
		
		final String[] celsX = new String[x];
		final String[] celsY = new String[y];
		
		for(int i=0; i<x; i++) {
			
			for(int j=0; j<y; j++) {
				
				if( ratoAtivo != null && i == ratoAtivo.getX() && j == ratoAtivo.getY() ) {
					mt.append(rato);
				}else if( i == memory.getSnakeX() && j == memory.getSnakeY() ) {
					mt.append(snake);
				}else {
					mt.append(unidade);
				}
			}
			
			mt.append("\n");
			
		}
		
		return mt;
		
	}
	
}

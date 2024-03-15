package br.com.mysnake.repo;

public class Coordenada {

	private int x,y;
	private boolean ativa;
	
	public Coordenada(int x, int y) {
		this.x = x;
		this.y = y;
		this.ativa = true;
	}
	
	public synchronized int getX() {
		return x;
	}

	public synchronized void setX(int x) {
		this.x = x;
		notify();
	}

	public synchronized int getY() {
		return y;
	}

	public synchronized void setY(int y) {
		this.y = y;
		notify();
	}

	public synchronized boolean isAtiva() {
		return ativa;
	}
	
	public synchronized void desativar() {
		this.ativa = false;
		notify();
	}
	
}

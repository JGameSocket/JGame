package app;

import fases.Fase1;
import service.GameServidor;

public class Main {
	
	public static void main(String[] args) {
		new GameServidor(9090).start();
		new Thread(new Fase1()).start();
	}
	
	// Main da classe Game.java
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Game frame = new Game();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/
	
}

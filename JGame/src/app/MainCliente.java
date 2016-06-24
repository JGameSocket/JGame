package app;

import fases.Fase1;

public class MainCliente {
	
	public static void main(String[] args) {
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

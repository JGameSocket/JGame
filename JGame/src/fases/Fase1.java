package fases;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import controle.ControlePerssonagem;
import jplay.GameImage;
import jplay.Keyboard;
import jplay.Window;
import listener.ClienteListener;
import lombok.Getter;
import lombok.Setter;
import modelo.Jogador;
import service.Cliente;
import service.Data.Action;

@Getter @Setter
public class Fase1 implements Runnable{
	
	private final String IP = "230.0.0.1";
	private final int PORTA = 9090;
	
	private Window janela;
	private GameImage back;
	private Keyboard teclado;
	
	private long horaAtual;
    private long horaUltima;
    private long deltaTime;
    
    private long lastTime = System.currentTimeMillis();
	private int frames = 0;
	
	private Jogador jogador;
	private ControlePerssonagem controleJogador;
	
	private Cliente cliente;
	private List<Jogador> jogadoresOnline;
	
	public Fase1(){
		this.janela = new Window(800, 600);
		this.back = new GameImage("res/fundo.jpg");
		this.teclado = this.janela.getKeyboard();
		this.horaAtual = System.currentTimeMillis();
		this.horaUltima = System.currentTimeMillis();
		this.deltaTime = 0;
		this.controleJogador = new ControlePerssonagem();
		String nome = JOptionPane.showInputDialog("Qual seu nome?");
		this.jogador = new Jogador("res/bola.png", 1, 50, 50, nome);
		try {
			this.cliente = new Cliente(InetAddress.getByName(IP), PORTA);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		this.jogadoresOnline = new ArrayList<Jogador>();
		new ClienteListener(this.cliente, this.jogador, this.jogadoresOnline).start();
	}
	
	public void run(){		
		while(true){
			
			this.back.draw();
			this.jogador.draw();
			this.controleJogador.mover(jogador, janela, teclado);
			this.cliente.enviarPacote(jogador.getInstanceData().setAction(Action.MOVER));
			
			if(this.jogadoresOnline.size() > 0){
				for(Jogador j : this.jogadoresOnline){
					j.draw();
				}
			}
			
			this.janela.update();
			//this.jogador.update();
			//janela.delay(1);
			
			fps();
		}
	}
	
	public void fps(){
		frames++;
		if (System.currentTimeMillis() - lastTime > 1000){
			lastTime += 1000;
			this.janela.setTitle("FPS: "+ frames);
			frames = 0;
		}
	}
	
	public double getDeltaTime(){
		horaAtual = System.currentTimeMillis();
		deltaTime = horaAtual - horaUltima;
		horaUltima = horaAtual;
		return deltaTime;
	}
	
}

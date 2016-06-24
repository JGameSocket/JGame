package service;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import modelo.Jogador;

@Getter @Setter
public class Data implements Serializable{
	private static final long serialVersionUID = 1L;
	private double x, y;
	private String nome;
	private Action action;
	
	public enum Action{
		ENVIAR_ALL, CADASTRAR, MOVER
	}
	
	public Data(String nome, double x, double y){
		this.x = x;
		this.y = y;
		this.nome = nome;
	}
	
	public Data setAction(Action action){
		this.action = action;
		return this;
	}
	
	public Jogador getInstanceJogador(){
		return new Jogador("res/bola.png", 1, this.x, this.y, nome);
	}
}

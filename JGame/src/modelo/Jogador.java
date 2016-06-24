package modelo;
import java.io.Serializable;

import jplay.Sprite;
import lombok.Getter;
import lombok.Setter;
import service.Data;

@Getter @Setter
public class Jogador extends Sprite implements Serializable{
	private static final long serialVersionUID = 1L;
	private String nome;
	
	public Jogador(String fileName, int numFrame, double x, double y, String nome) {
		super(fileName, numFrame);
		this.x = x;
		this.y = y;
		this.nome = nome;
	}
	
	public Data getInstanceData(){
		return new Data(this.nome, this.x, this.y);
	}
}

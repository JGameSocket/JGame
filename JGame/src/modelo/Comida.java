package modelo;

import jplay.GameObject;
import jplay.Sprite;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Comida extends Sprite{

	private boolean isComer;
	private int randomX;
	private int randomY;
	
	public Comida(String fileName, int x, int y){
		super(fileName);
		this.x = x;
		this.y = y;
	}
	
	public void colisao(GameObject obj){
		if(obj.collided(this)){
			this.isComer = true;
		}
		
		if(this.isComer){
			randomX = 10 + (int) (Math.random() * 780);
			randomY = 10 + (int) (Math.random() * 580);
			this.setX(randomX);
			this.setY(randomY);
			this.isComer = false;
		}
	}
	
}

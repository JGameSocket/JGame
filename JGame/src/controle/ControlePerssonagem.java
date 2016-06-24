package controle;

import jplay.Keyboard;
import jplay.Window;
import lombok.Getter;
import lombok.Setter;
import modelo.Jogador;

@Getter @Setter
public class ControlePerssonagem {
	private int direcao;
	private double movimento;
	private boolean mover;
	
	public ControlePerssonagem() {
		this.direcao = 5;
		this.movimento = 0.3;
		this.mover = false;
	}
	
	public void mover(Jogador perssonagem, Window janela, Keyboard teclado){

		if(teclado.keyDown(Keyboard.LEFT_KEY)){
			if(perssonagem.x > 0)
				perssonagem.x -= movimento;

			if(direcao != 1){
				//setCurrFrame(1);
				direcao = 1;
			}

		}

		if(teclado.keyDown(Keyboard.RIGHT_KEY)){
			if(perssonagem.x < janela.getWidth() - 50)
				perssonagem.x += movimento;

			if(direcao != 2){
				//setCurrFrame(0);
				direcao = 2;
			}

		}

		if(teclado.keyDown(Keyboard.UP_KEY)){
			if(perssonagem.y > 0)
				perssonagem.y -= movimento;

			if(direcao != 3){
				//setCurrFrame(0);
				direcao = 3;
			}

		}

		if(teclado.keyDown(Keyboard.DOWN_KEY)){
			if(perssonagem.y < janela.getHeight() - 50)
				perssonagem.y += movimento;

			if(direcao != 4){
				//setCurrFrame(1);
				direcao = 4;
			}

		}

	}
}

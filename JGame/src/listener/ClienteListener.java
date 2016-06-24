package listener;

import java.net.DatagramPacket;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import modelo.Jogador;
import service.Cliente;
import service.Data;
import util.ServiceUtil;

@Getter @Setter
public class ClienteListener extends Thread{
	
	private Cliente cliente;
	private Jogador jogador;
	private List<Jogador> jogadoresOnline;
	
	public ClienteListener(Cliente c, Jogador j, List<Jogador> jogadoresOnline){
		this.cliente = c;
		this.jogador = j;
		this.jogadoresOnline = jogadoresOnline;
	}
		
	public void run(){
		while(true){
			byte[] dados = new byte[1024];
			DatagramPacket pacote = new DatagramPacket(dados, dados.length);
			this.cliente.lerPacote(pacote);

			Data data = (Data) ServiceUtil.byteFromObject(pacote.getData());
			
			if(!data.getNome().equals(this.jogador.getNome()) && !this.cliente.existeJogador(this.jogadoresOnline, data)){
				jogadoresOnline.add(data.getInstanceJogador());
			}
			
		}
	}	
}

package service;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import modelo.Jogador;
import util.ServiceUtil;

@Getter @Setter
public class Cliente{
	
	private MulticastSocket multicastSocket; 
	private DatagramPacket pacote;
	private InetAddress ip;
	private int porta;
	
	public Cliente(InetAddress ip, int porta){
		try {
			this.multicastSocket = new MulticastSocket(porta);
			this.multicastSocket.joinGroup(ip);
			this.ip = ip;
			this.porta = porta;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	public boolean existeJogador(List<Jogador> jogadoresOnline, Data data){
		for (Jogador j : jogadoresOnline) {
			if(data.getNome().equals(j.getNome())){
				j.setX(data.getX());
				j.setY(data.getY());
				jogadoresOnline.set(jogadoresOnline.indexOf(j), j);
				return true;
			}
		}
		return false;
	}

	public void enviarPacote(Data jogador){
		byte[] dados = ServiceUtil.objectFromByte(jogador);
		try {	
			this.pacote = new DatagramPacket(dados, dados.length, ip, porta);
			multicastSocket.send(this.pacote);
			System.out.println("enviou o pacote para o servidor!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void lerPacote(DatagramPacket p){
		try {
			System.out.println("Aguardando resposta do servidor...");
			multicastSocket.receive(p);
			System.out.println("Pacote do servidor recebido no cliente!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

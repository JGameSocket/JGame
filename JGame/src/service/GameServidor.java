package service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import service.Data.Action;

@Getter @Setter
public class GameServidor extends Thread{
	
	private Map<String, DatagramPacket> jogadoresOnline = new HashMap<String, DatagramPacket>();
	
	private DatagramSocket servidorSocket;
	private DatagramPacket pacote;
	
	private InetAddress enderecoIP;
	private int porta;
	
	private byte[] dados;
	private Data data;
	
	//converter objeto em bytes
	private ByteArrayInputStream byteArrayInput;
	private ObjectInputStream input;
	
	public GameServidor(int porta){
		try {
			setName("ThreadServidor");
			servidorSocket = new DatagramSocket(porta);
			System.out.println("Porta criado com sucesso!");
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run(){
		while(true){

			dados = new byte[1024];
			pacote = new DatagramPacket(dados, dados.length);
			try {
				servidorSocket.receive(pacote);
				System.out.println("Pacote recebido com sucesso!");

				enderecoIP = pacote.getAddress();
				porta = pacote.getPort();
				System.out.println("Endereço IP cliente: "+enderecoIP+" Porta cliente: "+porta);

			} catch (IOException e) {
				e.printStackTrace();
			}

			//convertendo bytes para objeto
			data = deSerializar(pacote.getData());
			System.out.println("Dados deSerializado!");	

			Action action = data.getAction();
			if(action != null){
				if(action.equals(Action.CADASTRAR)){		
					addUsuario(data);
				}else if(action.equals(Action.MOVER)){
					enviarParaAll(dados);
				}
			}
		}

	}

	public void enviarParaAll(byte[] enviarDados){
		for(Map.Entry<String, DatagramPacket> kv : jogadoresOnline.entrySet()){
			enviarPacote(enviarDados, kv.getValue().getAddress(), kv.getValue().getPort());
		}
		System.out.println("Pacote enviado para todos os clientes!");
	}

	public void addUsuario(Data data){
		if(this.jogadoresOnline.containsKey(data.getNome())){
			System.out.println("Ja existe este nome no servidor");
		}else{
			jogadoresOnline.put(data.getNome(), pacote);
			System.out.println("Incluio no servidor: "+data.getNome());
		}
	}
	
	public void enviarPacote(byte[] enviarDados, InetAddress enderecoIP, int porta){
		
		pacote = new DatagramPacket(enviarDados, enviarDados.length, enderecoIP, porta);
		
		try {
			servidorSocket.send(pacote);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public byte[] serializar(Data jogador){
		
		ByteArrayOutputStream byteArray = new ByteArrayOutputStream(); 
		
		try {
			ObjectOutputStream output = new ObjectOutputStream (byteArray);
			output.writeObject(jogador);
			output.close();
			dados = byteArray.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return dados;
	}
	
	public Data deSerializar(byte[] enviarDados){
		
		Data joga = null;
		
		byteArrayInput = new ByteArrayInputStream(enviarDados);
		
		try {
			input = new ObjectInputStream(byteArrayInput);
			joga =  (Data) input.readObject();
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return joga;	
	}
	
}

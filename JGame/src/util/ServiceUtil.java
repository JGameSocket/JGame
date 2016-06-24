package util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import service.Data;

public class ServiceUtil {
	public static byte[] objectFromByte(Object objeto){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos;
		byte[] dados = new byte[1024];

		try {
			oos = new ObjectOutputStream (baos);
			oos.writeObject(objeto);
			oos.close();
			dados = baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return dados;
	}

	public static Object byteFromObject(byte[] dados){
		Object objeto = null;

		ByteArrayInputStream bais = new ByteArrayInputStream(dados);

		try {
			ObjectInputStream input = new ObjectInputStream(bais);
			objeto =  (Data) input.readObject();
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return objeto;	
	}
}

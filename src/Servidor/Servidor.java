package Servidor;

import java.io.*;
import java.net.*;

public class Servidor extends Thread {
	ServerSocket escucha;
	DataOutputStream fsalida = null, fsalidaOtro = null;
	DataInputStream fentrada = null;
	Socket cliente = null;

	public Servidor(ServerSocket pescucha) {
		escucha = pescucha;
	}

	public void cargo(DataOutputStream pfsalidaOtro) {
		fsalidaOtro = pfsalidaOtro;
	}

	public DataOutputStream Conectar() throws Exception {
		cliente = escucha.accept();
		fsalida = new DataOutputStream(cliente.getOutputStream());
		fentrada = new DataInputStream(cliente.getInputStream());

		System.out.println("Conectado");
		return fsalida;
	}

	public void run() {
		try {
			int cad = 0;
			while ((cad = fentrada.readInt()) != -1) {
				fsalidaOtro.writeInt(cad);
				//fsalidaOtro.flush();
			}
			fentrada.close();
			fsalida.close();
			cliente.close();
		} catch (Exception e) {
		}
	}

	public void informo(int orden) throws Exception {
		fsalida.writeInt(orden);
	}
}

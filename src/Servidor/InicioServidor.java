package Servidor;
import java.net.*;
import java.io.*;

public class InicioServidor {
  public static void main(String[] args) throws Exception{
    ServerSocket Demonio=new ServerSocket(3000);
    DataOutputStream fs1=null,fs2=null;
    Servidor s1;
    Servidor s2;
    System.out.println("Inicio Servidor");
    while (true){
    System.out.println("Sigo conectado");
      s1=new Servidor(Demonio);
      s2=new Servidor(Demonio);
      // Me aseguro que se conecten dos y sólo dos
      fs1=s1.Conectar(); // dev stream de salida de jugador 1
      fs2=s2.Conectar(); // dev strean de salida de jugador 2
      // cruzo los stream de salida e informo
      s1.cargo(fs2);
      s2.cargo(fs1);
      
      s1.informo(1);
      s2.informo(2);
      // Activo el funcionamiento cuando haya ya dos
      // sería interesante almacenar los objetos servidor para posteriores intervenciones
      System.out.println("Inicio el Juego");
      s1.start();
      s2.start();
      // espero a que terminen y cierro todo
      //s1.join();
      //s2.join();
    }
    //Demonio.close();
  }
}

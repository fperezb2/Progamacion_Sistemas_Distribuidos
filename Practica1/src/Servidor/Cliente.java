package Servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;

public class Cliente {
  public static void main(String[] args) {
    final String HOST = "127.0.0.1";
    final int PUERTO = 5001;
    DataInputStream in;
    DataOutputStream out;
    
    Scanner scanner = new Scanner(System.in);//Declaro el scanner para leer
    System.out.println("Dime el radio de tu circulo");
    double radio = scanner.nextDouble();//Guardo el radio del usuario
    scanner.close();
    
    try {
    	 
      Socket sc = new Socket(HOST, PUERTO);
      in = new DataInputStream(sc.getInputStream());
      out = new DataOutputStream(sc.getOutputStream());
     
      
      out.writeDouble(radio);//Envio al servidor el radio
      Double resultado = in.readDouble();//recido el resultado del servidor
      System.out.println("El area del circulo será: " + resultado);//Imprimo resultado
      sc.close();
    } catch (IOException ex) {
      Logger.getLogger(Servidor.class.getCanonicalName()).log(Level.SEVERE, null, ex);
    }
  }
}
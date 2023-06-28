package Servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {
  public static void main(String[] args) {
    ServerSocket servidor = null;
    Socket sc = null;
    DataInputStream in;
    DataOutputStream out;
    final int PUERTO = 5001;
    try {
      servidor = new ServerSocket(PUERTO);
      System.out.println("Servidor iniciado");
      Circulo circle = new Circulo();//Creo un objeto circulo
      while (true) {
        sc = servidor.accept();
        System.out.println("Cliente conectado");
        in = new DataInputStream(sc.getInputStream());
        out = new DataOutputStream(sc.getOutputStream());
       
       
        double radio = in.readDouble();//Recibo el radio del cliente
        System.out.println("Recibi un radio de " + radio);
        circle.setRadio(radio);//Seteo el radio a mi objeto circulo
        double resultado = circle.calculoArea();//Calculo el area
        System.out.println("El calculo que hice del area es " + resultado);
        out.writeDouble(resultado);//Envio el resultado al usuario
        sc.close();
        System.out.println("Cliente desconectado");
      }

    } catch (IOException ex) {
      Logger.getLogger(Servidor.class.getCanonicalName()).log(Level.SEVERE, null, ex);

    }
  }
}
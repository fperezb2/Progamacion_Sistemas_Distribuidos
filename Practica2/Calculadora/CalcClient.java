import MCalculadora.*;

import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import java.io.*;
import java.util.*;

public class CalcClient {
  static ICalculadora c = null;

  public static void main(String[] args) {
    try {

      // Se crea e inicializa el ORB
      ORB orb = ORB.init(args, null);

      // Se obtienen la raíz del contexto de nombres de CORBA
      org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");

      // Se recomienda el uso de NamingCOntextExt en lugar de NamingContext, ya que
      // es parte de la interoperabilidad en el servicio de nombres
      NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

      // Asignamos el nombre del servicio que estamos buscando para que sea
      // resuelto por el servicio de nombres
      String nombreServicio = "Calculadora";
      c = ICalculadoraHelper.narrow(ncRef.resolve_str(nombreServicio));

      Scanner scanner = new Scanner(System.in);// Declaro el scanner para leer

      // Llamada a los procedimientos remotos
      Operadores ops = new Operadores();
      System.out.println("Dime el primer numero para operar: ");
      ops.a = scanner.nextInt();
      System.out.println("Dime el segundo numero para operar: ");
      ops.b = scanner.nextInt();
      scanner.close();

      System.out.println("Suma: " + c.sumar(ops));
      System.out.println("Resta: " + c.restar(ops));
      System.out.println("Multiplicación: " + c.multiplicar(ops));
      System.out.println("División: " + c.dividir(ops));

      //// Hacemos uso del metodo modulo y lo sacamos por pantalla
      System.out.println("El resto de la división será: " + c.modulo(ops));
      // Hacemos uso del metodo exponencial y lo sacamos por pantalla
      System.out.println("El número " + ops.a + " elevado a la potencia " + ops.b + " : " + c.exponencial(ops));
      // Hacemos uso del metodo logNeperiano y lo sacamos por pantalla
      System.out.println("El logaritmo neperiano de la suma de ambos numeros será: " + c.logNeperiano(ops));

    } catch (Exception e) {
      e.printStackTrace();
      System.exit(-1);
    }
  }
}
import java.io.*;
import java.util.*;
import MCalculadora.*;

public class ImplementacionFunciones extends ICalculadoraPOA {
  public ImplementacionFunciones() {
    super();
  }

  public int sumar(Operadores ops) {
    return ops.a + ops.b;
  }

  public int restar(Operadores ops) {
    return ops.a - ops.b;
  }

  public int multiplicar(Operadores ops) {
    return ops.a * ops.b;
  }

  public int dividir(Operadores ops) {
    return ops.a / ops.b;
  }

  public int exponencial(Operadores ops) {// funcion para hacer el exponencial
    return (int) Math.pow(ops.a, ops.b);
  }

  public int logNeperiano(Operadores ops) {// devolverá el logaritmo neperiano de la suma de estos numeros
    return (int) Math.log(ops.a + ops.b);
  }

  public int modulo(Operadores ops) {// función para devolver el resto de la división en caso de que != 0
    return Math.floorMod(ops.a, ops.b);
  }

}
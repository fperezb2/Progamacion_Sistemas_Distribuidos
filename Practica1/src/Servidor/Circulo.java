package Servidor;

public class Circulo {
	
private double radio;	//Creo atributo privado para guardar radio
	
public Circulo(){//Contructor que inicializa el el radio a 0.0
	radio=0.0;
}

public double calculoArea() {//Funcion para calcular el area del circulo
	
	return Math.PI * Math.pow(radio,2);
}
public void setRadio(double radio) {//Funcion para setear radio
	this.radio = radio;
}
public double getRadio() {//Función para recivir el radio guardado
	return this.radio;
}
}

package unal.poo.practica;

import becker.robots.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/** 
 * Practica de los conceptos de Programacion Estructurada
 * @author Fabian Andres Giraldo */
public class Main
{    
       //Declaracion de Variables -- Forma temporal - No es buena practica tener
       //variables estaticas
              
	public static void main (String[] args){
 
            parqueadero P=new parqueadero();
            for(int i=1;i<10;i++)P.ingreso(new Carro("hol"+Integer.toString(i)));
            parqueadero.sacar("hol2",2);
            System.out.println("hol"+Integer.toString(2));
	}
}
package unal.poo.practica;
/** 
 * Ejercicio de parqueadero con Karel
 * @author Cristian Vargas, Diego Lopez, Jose Suarez  
 */
public class Main{        
	public static void main (String[] args){
            parqueadero P=new parqueadero();
            for(int i=1;i<15;i++)P.ingreso(new Carro("hol"+Integer.toString(i)));
            P.sacar("hol1",1);
            P.sacar("hol8",2);
            P.sacar("hol12",3);
            System.out.println("Los ingresos del dia son: "+P.getGanancias());
	}
}
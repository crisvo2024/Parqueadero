package unal.poo.practica;

import becker.robots.*;
import java.util.Date;
/**
 * @author: Cristian Vargas, Diego Lopez, Jose Suarez 
 */
public class parqueadero {
    private final City ciudad;
    private final Robot robot;
    private int t,ganancias;
    //se crean las secciones con la columna y el id
    private final seccion s1=new seccion(2,1,5);
    private final seccion s2=new seccion(4,2,5);
    private final seccion s3=new seccion(6,3,5);
    private final seccion temp=new seccion(7,4,4);
    private Date entrada;
    private static final int PRECIO=10;
    
    public parqueadero(){
        //creacion del escenario
        this.ciudad=new City();
        for (int i = 1; i < 11; i++) {
            Wall pared=new Wall(ciudad,1,i,Direction.NORTH);
            pared=new Wall(ciudad,6,i,Direction.SOUTH);
        }
        for (int i = 1; i < 7; i++) {
            Wall pared=new Wall(ciudad,i,1,Direction.WEST);
            if(i<6){
                pared=new Wall(ciudad,i,3,Direction.WEST);
                pared=new Wall(ciudad,i,2,Direction.EAST);
                pared=new Wall(ciudad,i,5,Direction.WEST);
                pared=new Wall(ciudad,i,4,Direction.EAST);
                pared=new Wall(ciudad,i,7,Direction.WEST);
                pared=new Wall(ciudad,i,6,Direction.EAST);
                pared=new Wall(ciudad,i,10,Direction.EAST);
            }
        }
        for (int i = 7; i < 11; i++) {
            Wall pared=new Wall(ciudad,5,i,Direction.NORTH);
            pared=new Wall(ciudad,4,i,Direction.SOUTH);
        }
        robot=new Robot(this.ciudad,6, 10, Direction.WEST);
    }
    /**
     * Metodo para hacer girar el robot
     * @param giros El parametro giros define la cantidad de veces que el robot gira a la izquierda
     */
    public void girar(int giros){
            for (int i=0;i<giros;i++)robot.turnLeft();
        }
    /**
     * Metodo para hacer avanzar el robot
     * @param veces define la cantidad de veces que el robot avanza
     */
    public void move(int veces){
        for (int i = 0; i < veces; i++)if(robot.frontIsClear())robot.move();
    }
    /**
     * Metodo que muestra las placas de los carros en una zona
     * @param z define la zona de la que se quieren mostrar las placas los carros
     */
    public void mostrarZona(int z){
        System.out.println("Los carros en la zona "+z+" son los de las placas: ");
        seccion zona;
            switch (z){
                case 1: zona=s1;
                    break;
                case 2: zona=s2;
                    break;
                case 3: zona=s3;
                    break;
                default: {
                    System.out.println("Zona no valida");
                    return;
                }
            }
        for (int i = 0; i < 5; i++) {
            if(zona.getCarros()[i]!=null)System.out.println(zona.getCarros()[i].getPlaca());
        }
    }
    /**
     * Metodo para ingresar carros al parqueadero
     * @param carro es el carro que se va a ingresar al parqueadero
     */
    public void ingreso(Carro carro){
        entrada=new Date();
        carro.setEntrada(entrada);
        seccion zona;
        if(s1.getLibres()>=s2.getLibres()&&s1.getLibres()>=s3.getLibres()&&s1.getLibres()!=0){
            zona=s1;
        }else {
            if(s2.getLibres()>=s1.getLibres()&&s2.getLibres()>=s3.getLibres()&&s2.getLibres()!=0){
                zona=s2;
            }else{
                if(s3.getLibres()>s1.getLibres()&&s3.getLibres()>s2.getLibres()&&s3.getLibres()!=0){
                    zona=s3;
                }else{
                    System.out.println("No hay parqueaderos libres vulva mas tarde");
                    return;
                }
            }     
        }

        carro.setPosicion(5-zona.getLibres());
        Carro[] nuevo=zona.getCarros();
        nuevo[5-zona.getLibres()]=carro;
        zona.setCarros(nuevo);

        move(10-zona.getColumna());
        girar(3);
        move(zona.getLibres());
        girar(1);
        move(1);
        Thing vehiculo=new Thing(ciudad,robot.getStreet(),robot.getAvenue());
        vehiculo.getIcon().setLabel(carro.getPlaca());
        girar(2);
        move(1);
        girar(3);
        move(zona.getLibres());
        zona.setLibres(zona.getLibres()-1);
        System.out.println("carro "+carro.getPlaca()+" parqueado en la zona "+zona.getId());
        girar(1);
        move(10-zona.getColumna());
        girar(2);
    }
    /**
     * Metodo sacar carros del parqueadero
     * @param placa define la placa del carro que se desea sacar
     * @param sec define la seccion en la que se encuentra el carro
     */
    public void sacar(String placa,int sec){
        Date now=new Date();
        Carro carro=null;
        seccion zona;
        switch (sec){
            case 1: zona=s1;
                break;
            case 2: zona=s2;
                break;
            case 3: zona=s3;
                break;
            default: {
                System.out.println("Zona no valida");
                return;
            }
        }
        for(int i=0;i<5-zona.getLibres();i++){
            if(zona.getCarros()[i].getPlaca().equals(placa)){
                carro=zona.getCarros()[i];
                i=5;
            }                      
        }
        if(carro==null){
            System.out.println("El carro no se encuentra en el parqueadero, revice la zona y la placa ingresadas");
            return;
        }
        
        float diferencia=(now.getTime()-carro.getEntrada().getTime())/1000;
        int minutos=(int)(diferencia/60);
        diferencia=diferencia-(minutos*60);
        System.out.println(minutos+":"+(int)diferencia);
        System.out.println("El valor a pagar es de: "+(minutos+Math.round(diferencia/60))*PRECIO);
        ganancias+=(minutos+Math.round(diferencia/60))*PRECIO;   

        for(int i=zona.getLibres()+1;i<5-carro.getPosicion();i++){
            move(10-zona.getColumna());
            girar(3);
            move(i);
            girar(1);
            move(1);
            while(robot.canPickThing())robot.pickThing();
            girar(2);
            move(1);
            girar(3);
            move(i);
            girar(1);
            move(temp.getColumna()-zona.getColumna()+(4-temp.getLibres()));
            girar(1);
            move(1);
            Thing vehiculo=new Thing(ciudad,robot.getStreet(),robot.getAvenue());
            vehiculo.getIcon().setLabel(zona.getCarros()[5-i].getPlaca());
            girar(2);
            move(1);
            girar(1);
            temp.setLibres(temp.getLibres()-1);
            move(temp.getLibres());
            girar(2);
        }

        move(10-zona.getColumna());
        girar(3);
        move(5-carro.getPosicion());
        girar(1);
        move(1);
        zona.getCarros()[carro.getPosicion()]=null;
        while(robot.canPickThing())robot.pickThing();
        girar(2);
        move(1);
        girar(3);
        move(5-carro.getPosicion());
        girar(1);
        move(10-zona.getColumna());
        girar(2);

        Carro nuevo[]=zona.getCarros();

        for(int i=5-carro.getPosicion();i>zona.getLibres()+1;i--){
            move(temp.getLibres());
            girar(3);
            move(1);
            while(robot.canPickThing())robot.pickThing();
            girar(2);
            move(1);
            girar(3);
            move(temp.getColumna()-zona.getColumna()+(4-temp.getLibres()));
            girar(3);
            move(i);
            girar(1);
            move(1);
            Thing vehiculo=new Thing(ciudad,robot.getStreet(),robot.getAvenue());
            vehiculo.getIcon().setLabel(zona.getCarros()[5-i+1].getPlaca());
            girar(2);
            move(1);
            girar(3);
            move(i);
            girar(1);
            move(10-zona.getColumna());
            girar(2);
            temp.setLibres(temp.getLibres()+1);
            if(i>0)nuevo[5-i]=zona.getCarros()[5-i+1];
            else;
        }
        zona.setCarros(nuevo);
    }
    /**
     * Metodo que retorna las ganancias actuales del parqueadero
     */
    public int getGanancias() {
        return ganancias;
    }
}

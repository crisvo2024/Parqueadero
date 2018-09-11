/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unal.poo.practica;

import becker.robots.*;
import java.util.Date;

/**
 *
 * @author Estudiante
 */
public class parqueadero {
    private static City ciudad;
    private static Robot robot;
    private static int z1=5,z2=5,z3=5,t=4,ganancias=0;
    private static Carro ubicacion[][]=new Carro[5][4];
    //se crean las secciones con la columna y el id
    private static seccion s1=new seccion(2,1,5);
    private static seccion s2=new seccion(4,2,5);
    private static seccion s3=new seccion(6,3,5);
    private static seccion temp=new seccion(7,4,4);
    private static Date entrada;
    private static final int precio=10;
    /**
     * Descripcion del metodo
     * @param 
     * 
     */
    public parqueadero(){
        this.ciudad=new City();
        for (int i = 1; i < 11; i++) {
            Wall pared=new Wall(ciudad,1,i,Direction.NORTH);
            Wall pared1=new Wall(ciudad,6,i,Direction.SOUTH);
        }
        for (int i = 1; i < 7; i++) {
            Wall pared=new Wall(ciudad,i,1,Direction.WEST);
            if(i<6){
                Wall pared1=new Wall(ciudad,i,3,Direction.WEST);
                Wall pared2=new Wall(ciudad,i,2,Direction.EAST);
                Wall pared3=new Wall(ciudad,i,5,Direction.WEST);
                Wall pared4=new Wall(ciudad,i,4,Direction.EAST);
                Wall pared5=new Wall(ciudad,i,7,Direction.WEST);
                Wall pared6=new Wall(ciudad,i,6,Direction.EAST);
                Wall pared7=new Wall(ciudad,i,10,Direction.EAST);
            }
        }
        for (int i = 7; i < 11; i++) {
            Wall pared1=new Wall(ciudad,5,i,Direction.NORTH);
            Wall pared2=new Wall(ciudad,4,i,Direction.SOUTH);
        }
        parqueadero.robot=new Robot(this.ciudad,6, 10, Direction.WEST);
    }
    public static void girar(int giros){
            for (int i=0;i<giros;i++)parqueadero.robot.turnLeft();
        }
        public static void move(int parametroEntrada){
            for (int i = 0; i < parametroEntrada; i++)if(parqueadero.robot.frontIsClear())parqueadero.robot.move();
        }
        public static void mostrarZona(int z){
            System.out.println("Los carros en la zona "+z+" son los de las placas: ");
            for (int i = 1; i < 6; i++) {
                if(ubicacion[i][z]!=null)System.out.println(ubicacion[i][z]+", ");
            }
        }
        public static void imp_mtz(int[][] m){
            for(int i=0;i<m.length;i++){
                for (int j = 0; j <m[i].length; j++)System.out.print(m[i][j]+" ");
                System.out.println(" ");
            }
        }
        public static void ingreso(Carro carro){
            entrada=new Date();
            carro.setEntrada(entrada);
            if(s1.getLibres()>=s2.getLibres()&&s1.getLibres()>=s3.getLibres()&&s1.getLibres()!=0){
                carro.setPosicion(5-s1.getLibres());
                Carro[] nuevo=s1.getCarros();
                nuevo[5-s1.getLibres()]=carro;
                s1.setCarros(nuevo);
                t=8;
            }
            else if(s2.getLibres()>=s1.getLibres()&&s2.getLibres()>=s3.getLibres()&&s2.getLibres()!=0){
                carro.setPosicion(5-s2.getLibres());
                Carro[] nuevo=s2.getCarros();
                nuevo[5-s2.getLibres()]=carro;
                s2.setCarros(nuevo);
                t=6;
                
            }
            else if(s3.getLibres()>s1.getLibres()&&s3.getLibres()>s2.getLibres()&&s3.getLibres()!=0){
                carro.setPosicion(5-s3.getLibres());
                Carro[] nuevo=s3.getCarros();
                nuevo[5-s3.getLibres()]=carro;
                s3.setCarros(nuevo);
                t=4;
            }
            if(s1.getLibres()==0&&s2.getLibres()==0&&s3.getLibres() == 0){
                System.out.println("No hay parqueaderos libres vulva mas tarde");
                return;
            }
                move(t);
                girar(3);
                if(t==8)move(s1.getLibres());
                if(t==6)move(s2.getLibres());
                if(t==4)move(s3.getLibres());
                girar(1);
                move(1);
                Thing vehiculo=new Thing(parqueadero.ciudad,parqueadero.robot.getStreet(),parqueadero.robot.getAvenue());
                vehiculo.getIcon().setLabel(carro.getPlaca());
                girar(2);
                move(1);
                girar(3);
                if(t==8){
                    move(s1.getLibres());
                    s1.setLibres(s1.getLibres()-1);
                    System.out.println("carro "+carro.getPlaca()+" parqueado en la zona 1");
                }
                if(t==6){
                    move(s2.getLibres());
                    s2.setLibres(s2.getLibres()-1);
                    System.out.println("carro "+carro.getPlaca()+" parqueado en la zona 2");
                }
                if(t==4){
                    move(s3.getLibres());
                    s3.setLibres(s3.getLibres()-1);
                    System.out.println("carro "+carro.getPlaca()+" parqueado en la zona 3");
                }
                girar(1);
                move(t);
                girar(2);
        }
        
        
        public static void sacar(String placa,int sec){
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
            /*
                ubc es la posicion del carro en el arreglo
                prim es la uicacion del ultimo carro en entrar+
            */
            
            
            
            float diferencia=(now.getTime()-carro.getEntrada().getTime())/1000;
            int minutos=(int)(diferencia/60);
            diferencia=diferencia-(minutos*60);
            System.out.println(minutos+":"+(int)diferencia);
            System.out.println("El valor a pagar es de: "+(minutos+Math.round(diferencia/60))*precio);
            ganancias+=(minutos+Math.round(diferencia/60))*precio;    
            for(int i=zona.getLibres()+1;i<5-carro.getPosicion();i++){
                move(10-zona.getColumna());
                girar(3);
                move(i);
                girar(1);
                move(1);
                while(parqueadero.robot.canPickThing())parqueadero.robot.pickThing();
                girar(2);
                move(1);
                girar(3);
                move(i);
                girar(1);
                move(temp.getColumna()-zona.getColumna()+(4-temp.getLibres()));
                girar(1);
                move(1);
                Thing vehiculo=new Thing(parqueadero.ciudad,parqueadero.robot.getStreet(),parqueadero.robot.getAvenue());
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
            while(parqueadero.robot.canPickThing())parqueadero.robot.pickThing();
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
                while(parqueadero.robot.canPickThing())parqueadero.robot.pickThing();
                girar(2);
                move(1);
                girar(3);
                move(temp.getColumna()-zona.getColumna()+(4-temp.getLibres()));
                girar(3);
                move(i);
                girar(1);
                move(1);
                Thing vehiculo=new Thing(parqueadero.ciudad,parqueadero.robot.getStreet(),parqueadero.robot.getAvenue());
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
                        
        }
}

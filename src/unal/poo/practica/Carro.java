package unal.poo.practica;

import java.util.Date;
/**
 * 
 * @author Cristian Vargas, Diego Lopez, Jose Suarez
 */
public class Carro {
    private final String placa;
    private Date entrada;
    private int posicion;
    /**
     * Constructor para un carro
     * @param placa define la placa del carro
     */
    public Carro(String placa) {
        this.placa = placa;
    }

    public void setEntrada(Date entrada) {
        this.entrada = entrada;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public String getPlaca() {
        return placa;
    }

    public Date getEntrada() {
        return entrada;
    }
    
}

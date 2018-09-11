package unal.poo.practica;


import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Asus
 */
public class Carro {
    private String placa;
    private Date entrada;
    private int posicion;

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

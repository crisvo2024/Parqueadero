package unal.poo.practica;

/**
 *
 * @author Cristian Vargas, Diego Lopez, Jose Suarez
 */
public class seccion {
    private final int columna;
    private int id, libres;
    private Carro[] carros;

    public seccion(int columna, int id, int libres) {
        this.columna = columna;
        this.id = id;
        this.libres = libres;
        this.carros = new Carro[5];
    }

    public int getLibres() {
        return libres;
    }

    public void setLibres(int libres) {
        this.libres = libres;
    }

    public int getColumna() {
        return columna;
    }
    
    public Carro[] getCarros() {
        return carros;
    }

    public void setCarros(Carro[] carros) {
        this.carros = carros;
    }
    
}
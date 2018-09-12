package unal.poo.practica;
/**
 *
 * @author Cristian Vargas, Diego Lopez, Jose Suarez
 */
public class seccion {
    private final int columna;
    private int libres;
    private Carro[] carros;
    /**
     * Constructor para una seccion
     * @param columna define la columna en la que esta unbicada la seccion
     * @param libres define la cantidad de espacios libres en la seccion
     */
    public seccion(int columna, int libres) {
        this.columna = columna;
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
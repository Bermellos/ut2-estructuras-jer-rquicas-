package com.example;

public class Nave implements Comparable<Nave> {

    private int codigo;
    private String clase;
    private int cantidadCombustible;

    public Nave(int codigo, String clase, int cantidadCombustible){
        this.codigo = codigo;
        this.clase = clase;
        this.cantidadCombustible = cantidadCombustible;
    }

    public int getCantidadCombustible() {
        return cantidadCombustible;
    }

    public String getClase() {
        return clase;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCantidadCombustible(int cantidadCombustible) {
        this.cantidadCombustible = cantidadCombustible;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @Override 
    public int compareTo(Nave otra) { // Se implementa para que Nave pueda ser comparada y ordenada en el árbol según su código.
        return Integer.compare(this.codigo, otra.codigo);
    }
}

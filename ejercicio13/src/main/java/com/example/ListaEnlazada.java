package com.example;

import java.util.function.Consumer;

public class ListaEnlazada<T> implements TDALista<T>{

    private Nodo<T> cabeza;
    
    public ListaEnlazada(){
        cabeza = null;
    }

    @Override
    public boolean agregar(T dato) {
        Nodo<T> nuevo = new Nodo<T>(dato);

        if(cabeza == null){
            cabeza = nuevo;
        }else{
            Nodo<T> temp = cabeza;
            while (temp.getSiguiente() !=null) {
                temp = temp.getSiguiente();
            }
            temp.setSiguiente(nuevo);

        }
        return true;
    }


    @Override
    public int tamano() 
    {
        int contador = 0;
        Nodo<T> actual = cabeza;

        while (actual != null) 
        {
            contador++;
            actual = actual.getSiguiente();
        }

        return contador;
    }

    
    @Override
    public void recorrer(Consumer<T> consumidor) {
        Nodo<T> actual = cabeza;

        while (actual != null) {
            consumidor.accept(actual.getDato());
            actual = actual.getSiguiente();
        }
    }

}

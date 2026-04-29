package com.example;
import java.util.function.Consumer;

/**
 * Modela un nodo del árbol binario.
 * La implementación de esta estructura debe ser recursiva.
 */
public interface TDAElemento <T>{

    /**
     * Asigna el nodo izquierdo del nodo actual. Puede ser nulo.
     */
    void setHijoIzquierdo(TDAElemento<T> hijoIzquierdo);

    /**
     * Asigna el nodo derecho del nodo actual. Puede ser nulo.
     */
    void setHijoDerecho(TDAElemento<T> hijoDerecho);

    /**
     * Devuelve el hijo derecho del nodo actual. El valor es nulo si no tiene hijo derecho.
     */
    TDAElemento<T> getHijoIzquierdo();

    /**
     * Devuelve el hijo izquierdo del nodo actual. El valor es nulo si no tiene hijo izquierdo.
     */
    TDAElemento<T> getHijoDerecho();

    /**
     * Actualiza el dato del nodo actual.
     */
    void setDato(T dato);

    /**
     * devuelve el dato del nodo actual.
     */
    T getDato();


    /**
     * Agrega un nuevo elemento al árbol
     * Si el nuevoDato existe, no se agrega
     */
    boolean insertar(Comparable<T> nuevoDato);

    /**
     * {@snippet :
     * // ejemplo de uso
     * elemento.inOrder(dato ->{
     *     // procesar dato
     *     // esta función se llama tantas veces como nodos halla en el árbol
     * });
     *}
     */
    void inOrder(Consumer<TDAElemento<T>> consumidor);

    int altura();
}

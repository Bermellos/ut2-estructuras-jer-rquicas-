package com.example;

import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Define un Tipo de Dato Abstracto (TDA) Lista genérica.
 *
 * <p>Una lista permite almacenar elementos en una secuencia ordenada por posición,
 * admitiendo inserciones, accesos, eliminaciones, búsquedas y operaciones de ordenamiento.</p>
 *
 * <p>Las posiciones de los elementos se interpretan mediante índices enteros.
 * Salvo que la implementación indique lo contrario, se asume indexación basada en 0.</p>
 *
 * @param <T> el tipo de los elemen tos almacenados en la lista
 */
public interface TDALista<T> {

    /**
     * Agrega un elemento al final de la lista.
     *
     * @param elem el elemento a agregar
     * @return 
     */
    boolean agregar(T elem);

    /**
     * Retorna la cantidad de elementos almacenados en la lista.
     *
     * @return la cantidad de elementos de la lista
     */
    int tamano();

    void recorrer(Consumer<T> consumidor);
}

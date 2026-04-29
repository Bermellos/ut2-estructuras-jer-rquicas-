package com.example;

import java.util.function.Consumer;

/**
 * Define un Tipo de Dato Abstracto (TDA) Árbol Binario genérico.
 *
 * <p>Un árbol binario es una estructura de datos jerárquica en la que cada nodo
 * puede tener como máximo dos hijos: un hijo izquierdo y un hijo derecho.</p>
 *
 * <p>Esta interfaz proporciona operaciones para insertar, buscar, eliminar elementos,
 * así como diferentes formas de recorrido del árbol (in-order, pre-order, post-order)
 * y métodos para obtener información sobre la estructura del árbol.</p>
 *
 * @param <T> el tipo de los elementos almacenados en el árbol
 */
public interface TDAArbolBinario<T> {
    
    /**
     * Retorna el elemento raíz del árbol.
     *
     * @return el elemento raíz del árbol, o {@code null} si el árbol está vacío
     */
    TDAElemento<T> obtenerRaiz();

    /**
     * Agrega un dato al árbol.
     *
     * <p>Si el dato ya existe en el árbol, no se agrega nuevamente.</p>
     *
     * @param dato el elemento a insertar
     * @return {@code true} si el elemento fue agregado correctamente;
     * {@code false} si el elemento ya existía y no fue agregado
     */
    boolean insertar(Comparable<T> dato);

    /**
     * Recorre el árbol en in-order
     * {@snippet :
     * // ejemplo de uso
     * elemento.inOrder(dato ->{
     *     // procesar dato
     *     // esta función se llama tantas veces como nodos halla en el árbol
     * });
     *}
     */
    void inOrder(Consumer<T> consumidor);

    /**
     * Devuelve la altura del arbol
     */
    int altura();

}



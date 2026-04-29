package com.example;

import java.util.function.Consumer;

public class ABBImpl<T> implements TDAArbolBinario<T>{
    protected TDAElemento<T> raiz;

    @Override
    public TDAElemento<T> obtenerRaiz() {
        return this.raiz;
    }


    // ─── Insertar ────────────────────────────────────────────────────

    @Override
    public boolean insertar(Comparable<T> dato) {
        if (raiz == null) {
            raiz = new ElementoABBImpl<>((T) dato);
            return true;
        } else {
            return raiz.insertar(dato);
        }
    }

    // ─── Recorridos ──────────────────────────────────────────────────

    @Override
    public void inOrder(Consumer<T> consumidor) {
        if (raiz != null) {
            raiz.inOrder(nodo -> consumidor.accept(nodo.getDato()));
        }
    }

    @Override
    public int altura() {
        if (raiz == null) return 0;
        return raiz.altura(); // altura de la raíz = altura del árbol
    }

}
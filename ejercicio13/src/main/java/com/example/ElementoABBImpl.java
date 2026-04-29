package com.example;
import java.util.function.Consumer;


public class ElementoABBImpl<T> implements TDAElemento<T> {
    private T dato;
    private TDAElemento<T> hijoIzq;
    private TDAElemento<T> hijoDer;

    public ElementoABBImpl(T dato) {
        this.dato = dato;
        this.hijoIzq = null;
        this.hijoDer = null;
    }

    // ─── Getters y Setters ───────────────────────────────────────────

    @Override
    public void setHijoIzquierdo(TDAElemento<T> hijoIzquierdo) {
        this.hijoIzq = hijoIzquierdo;
    }

    @Override
    public void setHijoDerecho(TDAElemento<T> hijoDerecho) {
        this.hijoDer = hijoDerecho;
    }

    @Override
    public TDAElemento<T> getHijoIzquierdo() {
        return this.hijoIzq;
    }

    @Override
    public TDAElemento<T> getHijoDerecho() {
        return this.hijoDer;
    }

    @Override
    public void setDato(T dato) {
        this.dato = dato;
    }

    @Override
    public T getDato() {
        return this.dato;
    }


    // ─── Insertar ────────────────────────────────────────────────────

    @Override
    public boolean insertar(Comparable<T> nuevoDato) {
        if (nuevoDato.compareTo(this.dato) > 0) {
            if (hijoDer == null) {
                hijoDer = new ElementoABBImpl<>((T) nuevoDato);
                return true;
            } else {
                return hijoDer.insertar(nuevoDato);
            }
        } else if (nuevoDato.compareTo(this.dato) < 0) {
            if (hijoIzq == null) {
                hijoIzq = new ElementoABBImpl<>((T) nuevoDato);
                return true;
            } else {
                return hijoIzq.insertar(nuevoDato);
            }
        }
        return false; // ya existe
    }


    // ─── Recorridos ──────────────────────────────────────────────────

    @Override
    public void inOrder(Consumer<TDAElemento<T>> consumidor) {
        if (this.hijoIzq != null) this.hijoIzq.inOrder(consumidor);
        consumidor.accept(this);
        if (this.hijoDer != null) this.hijoDer.inOrder(consumidor);
    }

    // ─── Utilidades ──────────────────────────────────────────────────
    @Override
    public int altura() {
        int altIzq = (this.hijoIzq != null) ? this.hijoIzq.altura() : 0;
        int altDer = (this.hijoDer != null) ? this.hijoDer.altura() : 0;
        return 1 + Math.max(altIzq, altDer);
    }

}

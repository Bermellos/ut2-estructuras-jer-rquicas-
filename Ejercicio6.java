package com.example;

import java.util.ArrayList;

public class Ejercicio6 
{
    public static class Nodo 
    {
        Nodo izquierdo;
        Nodo derecho; 
        int dato; 

        public Nodo(int dato)
        {
            this.izquierdo = null;
            this.derecho = null; 
            this.dato = dato; 
        }
    }

    Nodo raiz;

    public static int altura(Nodo nodo)
    {
        if (nodo == null)
        {
            return 0;
        }

        int alturaIzq = altura(nodo.izquierdo);
        int alturaDer = altura(nodo.derecho);

        return 1 + Math.max(alturaIzq, alturaDer);
    }

    public static int tamaño(Nodo nodo) // Devuelve la cantidad de elementos total del árbol
    {
        if (nodo == null)
        {
            return 0; 
        }

        int tamañoIzq = tamaño(nodo.izquierdo);
        int tamañoDer = tamaño(nodo.derecho);

        return 1 + tamañoDer + tamañoIzq;
    }

    public static int hojas(Nodo nodo)
    {
        if (nodo == null)
        {
            return 0;
        }

        if (nodo.izquierdo == null && nodo.derecho == null) // si los dos no tienen hijos, osea son null (izq y der), es una hoja
        {
            return 1;  // el return 1 indica que encontró una hoja y nos interesa contarla 
        }

        int hojasIzq = hojas(nodo.izquierdo);
        int hojasDer = hojas(nodo.derecho);

        return hojasIzq + hojasDer; // NO pongo 1 + ... porque SI se cuenta en los casos base
    }

    public static int internos(Nodo nodo)
    {
        if (nodo == null)
        {
            return 0;
        }

        if (nodo.izquierdo == null && nodo.derecho == null)  // Osea, si es una hoja retorna 0
        {
            return 0; // el return 0 indica que no nos interesa contar la hoja 
        }

        int internosIzq = internos(nodo.izquierdo);
        int internosDer = internos(nodo.derecho);

        return 1 + internosIzq + internosDer; // SI pongo 1 + ... porque NO se cuenta en los casos base
    }

    public ArrayList<Integer> completos(Nodo nodo)
    {
        ArrayList<Integer> listaCompletos = new ArrayList<Integer>(); 

        if (nodo == null)
        {
            return listaCompletos;
        }

        if (nodo.izquierdo != null && nodo.derecho != null)  // los nodos completos son los que tienen ambos hijos
        {
            listaCompletos.add(nodo.dato); // como nos interesan los nodos completos, los agrego a la lista
        }

        ArrayList<Integer> listaIzq = completos(nodo.izquierdo);
        ArrayList<Integer> listaDer = completos(nodo.derecho);

        listaCompletos.addAll(listaIzq); // add all porque agrega todos los datos de la lista de la recursión, no como antes que era un dato solo 
        listaCompletos.addAll(listaDer);

        return listaCompletos;
    }


    public ArrayList<Integer> enNivel(Nodo nodo, int nivel)
    {
        ArrayList<Integer> lista = new ArrayList<Integer>();

        if (nodo == null)
        {
            return lista; 
        }

        if (nivel == 0)
        {
            lista.add(nodo.dato);
            return lista; 
        }

        ArrayList<Integer> listaIzq = enNivel(nodo.izquierdo, nivel-1);  // vas restando el nivel hasta llegar al 0 y así conocer los datos posteriores
        ArrayList<Integer> listaDer = enNivel(nodo.derecho, nivel-1);

        lista.addAll(listaIzq);
        lista.addAll(listaDer);

        return lista; 

    }

    public static void main(String args[])
    {
        Ejercicio6 arbol = new Ejercicio6();
        arbol.raiz = new Nodo(5);
        arbol.raiz.derecho = new Nodo(6);
        arbol.raiz.izquierdo = new Nodo(8);
        arbol.raiz.izquierdo.derecho = new Nodo(15);
        arbol.raiz.derecho.izquierdo = new Nodo(20);
    }

}
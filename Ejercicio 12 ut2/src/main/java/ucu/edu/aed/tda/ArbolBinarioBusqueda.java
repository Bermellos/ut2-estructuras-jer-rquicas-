package ucu.edu.aed.tda;

import java.util.function.Consumer;

/**
 * Implementación de un Árbol Binario de Búsqueda (ABB).
 * Los elementos se organizan de manera que para cada nodo,
 * todos los elementos del subárbol izquierdo son menores
 * y todos los del subárbol derecho son mayores.
 *
 * @param <T> el tipo de los elementos almacenados en el árbol
 */
public class ArbolBinarioBusqueda<T extends Comparable<T>> implements TDAArbolBinario<T> {
    private TDAElemento<T> raiz;

    /**
     * Construye un árbol binario de búsqueda vacío.
     */
    public ArbolBinarioBusqueda() {
        this.raiz = null;
    }

    /**
     * Busca un elemento en el árbol usando el predicado dado.
     *
     * @param predicate el criterio de búsqueda
     * @return el elemento encontrado, o null si no existe
     */
    @Override
    public T buscar(Comparable<T> predicate) {
        if (raiz == null) {
            return null;
        }
        TDAElemento<T> nodo = raiz.buscar(predicate);
        return nodo != null ? nodo.getDato() : null;
    }

    /**
     * Retorna el elemento raíz del árbol.
     *
     * @return la raíz del árbol, o null si el árbol está vacío
     */
    @Override
    public TDAElemento<T> obtenerRaiz() {
        return raiz;
    }

    /**
     * Elimina todos los nodos que cumplen con el criterio de búsqueda.
     *
     * @param criterioBusqueda el criterio para identificar los elementos a eliminar
     * @return true si al menos un elemento fue eliminado
     */
    @Override
    public boolean eliminar(Comparable<T> criterioBusqueda) {
        if (raiz == null) {
            return false;
        }
        
        TDAElemento<T> eliminado = raiz.eliminar(criterioBusqueda);
        if (eliminado != null) {
            if (eliminado.getDato().equals(raiz.getDato())) {
                // Actualizar la raíz si se elimina
                if (raiz.getHijoIzquierdo() != null) {
                    raiz = raiz.getHijoIzquierdo();
                } else {
                    raiz = raiz.getHijoDerecho();
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Inserta un nuevo elemento en el árbol.
     * Si el elemento ya existe, no se inserta nuevamente.
     *
     * @param dato el elemento a insertar
     * @return true si se insertó correctamente, false si ya existía
     */
    @Override
    public boolean insertar(Comparable<T> dato) {
        if (raiz == null) {
            raiz = new Nodo<>((T) dato);
            return true;
        }
        return raiz.insertar(dato);
    }

    /**
     * Recorre el árbol en in-order (izquierda-raíz-derecha).
     *
     * @param consumidor la función a aplicar a cada elemento
     */
    @Override
    public void inOrder(Consumer<T> consumidor) {
        if (raiz != null) {
            raiz.inOrder(nodo -> consumidor.accept(nodo.getDato()));
        }
    }

    /**
     * Recorre el árbol en pre-order (raíz-izquierda-derecha).
     *
     * @param consumidor la función a aplicar a cada elemento
     */
    @Override
    public void preOrder(Consumer<T> consumidor) {
        if (raiz != null) {
            raiz.preOrder(nodo -> consumidor.accept(nodo.getDato()));
        }
    }

    /**
     * Recorre el árbol en post-order (izquierda-derecha-raíz).
     *
     * @param consumidor la función a aplicar a cada elemento
     */
    @Override
    public void postOrder(Consumer<T> consumidor) {
        if (raiz != null) {
            raiz.postOrder(nodo -> consumidor.accept(nodo.getDato()));
        }
    }

    /**
     * Verifica si el árbol está vacío.
     *
     * @return true si el árbol no tiene elementos
     */
    @Override
    public boolean esVacio() {
        return raiz == null;
    }

    /**
     * Retorna la cantidad de nodos en el árbol.
     *
     * @return el número de nodos
     */
    @Override
    public int cantidadNodos() {
        if (raiz == null) {
            return 0;
        }
        return ((Nodo<T>) raiz).cantidadNodos();
    }

    /**
     * Retorna la cantidad de hojas en el árbol.
     *
     * @return el número de hojas
     */
    @Override
    public int cantidadHojas() {
        if (raiz == null) {
            return 0;
        }
        return raiz.cantidadHojas();
    }

    /**
     * Retorna la cantidad de nodos internos en el árbol.
     *
     * @return el número de nodos internos
     */
    @Override
    public int cantidadNodosInternos() {
        if (raiz == null) {
            return 0;
        }
        return raiz.cantidadNodosInternos();
    }
}

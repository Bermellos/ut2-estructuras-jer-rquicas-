package ucu.edu.aed.tda;

import java.util.function.Consumer;

/**
 * Implementación de un nodo para un Árbol Binario de Búsqueda.
 * Esta implementación es recursiva y funciona con cualquier tipo genérico.
 *
 * @param <T> el tipo de los elementos almacenados en el nodo
 */
public class Nodo<T> implements TDAElemento<T> {
    private T dato;
    private TDAElemento<T> hijoIzquierdo;
    private TDAElemento<T> hijoDerecho;

    /**
     * Construye un nodo con el dato proporcionado.
     * @param dato el elemento a almacenar en el nodo
     */
    public Nodo(T dato) {
        this.dato = dato;
        this.hijoIzquierdo = null;
        this.hijoDerecho = null;
    }

    @Override
    public void setHijoIzquierdo(TDAElemento<T> hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

    @Override
    public void setHijoDerecho(TDAElemento<T> hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }

    @Override
    public TDAElemento<T> getHijoIzquierdo() {
        return hijoIzquierdo;
    }

    @Override
    public TDAElemento<T> getHijoDerecho() {
        return hijoDerecho;
    }

    @Override
    public void setDato(T dato) {
        this.dato = dato;
    }

    @Override
    public T getDato() {
        return dato;
    }

    /**
     * Busca un elemento en el subárbol enraizado en este nodo.
     * La búsqueda se realiza comparando el criterio con el dato actual.
     *
     * @param criterioBusqueda el criterio para la búsqueda
     * @return el nodo que contiene el elemento, o null si no se encuentra
     */
    @Override
    public TDAElemento<T> buscar(Comparable<T> criterioBusqueda) {
        int comparacion = criterioBusqueda.compareTo(dato);
        
        if (comparacion == 0) {
            return this;
        } else if (comparacion < 0) {
            // Buscar en el subárbol izquierdo
            if (hijoIzquierdo != null) {
                return hijoIzquierdo.buscar(criterioBusqueda);
            }
        } else {
            // Buscar en el subárbol derecho
            if (hijoDerecho != null) {
                return hijoDerecho.buscar(criterioBusqueda);
            }
        }
        return null;
    }

    /**
     * Elimina un nodo del subárbol según el criterio de búsqueda.
     * Maneja tres casos: nodo hoja, nodo con un hijo, nodo con dos hijos.
     *
     * @param criterioBusqueda el criterio para identificar el nodo a eliminar
     * @return el nodo eliminado, o null si no se encuentra
     */
    @Override
    public TDAElemento<T> eliminar(Comparable<T> criterioBusqueda) {
        int comparacion = criterioBusqueda.compareTo(dato);
        
        if (comparacion < 0) {
            // Eliminar del subárbol izquierdo
            if (hijoIzquierdo != null) {
                TDAElemento<T> eliminado = hijoIzquierdo.eliminar(criterioBusqueda);
                if (eliminado != null && hijoIzquierdo.getDato().equals(eliminado.getDato())) {
                    // Ajustar el hijo izquierdo
                    if (hijoIzquierdo.getHijoIzquierdo() != null) {
                        hijoIzquierdo = hijoIzquierdo.getHijoIzquierdo();
                    } else {
                        hijoIzquierdo = hijoIzquierdo.getHijoDerecho();
                    }
                }
                return eliminado;
            }
        } else if (comparacion > 0) {
            // Eliminar del subárbol derecho
            if (hijoDerecho != null) {
                TDAElemento<T> eliminado = hijoDerecho.eliminar(criterioBusqueda);
                if (eliminado != null && hijoDerecho.getDato().equals(eliminado.getDato())) {
                    // Ajustar el hijo derecho
                    if (hijoDerecho.getHijoIzquierdo() != null) {
                        hijoDerecho = hijoDerecho.getHijoIzquierdo();
                    } else {
                        hijoDerecho = hijoDerecho.getHijoDerecho();
                    }
                }
                return eliminado;
            }
        } else {
            // Se encontró el nodo a eliminar
            return this;
        }
        return null;
    }

    /**
     * Inserta un nuevo elemento en el subárbol enraizado en este nodo.
     * Si el elemento ya existe, no se inserta nuevamente.
     *
     * @param nuevoDato el elemento a insertar
     * @return true si se insertó correctamente, false si ya existía
     */
    @Override
    public boolean insertar(Comparable<T> nuevoDato) {
        int comparacion = nuevoDato.compareTo(dato);
        
        if (comparacion == 0) {
            // El elemento ya existe
            return false;
        } else if (comparacion < 0) {
            // Insertar en el subárbol izquierdo
            if (hijoIzquierdo == null) {
                hijoIzquierdo = new Nodo<>((T) nuevoDato);
                return true;
            } else {
                return hijoIzquierdo.insertar(nuevoDato);
            }
        } else {
            // Insertar en el subárbol derecho
            if (hijoDerecho == null) {
                hijoDerecho = new Nodo<>((T) nuevoDato);
                return true;
            } else {
                return hijoDerecho.insertar(nuevoDato);
            }
        }
    }

    /**
     * Recorre el subárbol en in-order (izquierda-raíz-derecha).
     *
     * @param consumidor la función a aplicar a cada nodo
     */
    @Override
    public void inOrder(Consumer<TDAElemento<T>> consumidor) {
        if (hijoIzquierdo != null) {
            hijoIzquierdo.inOrder(consumidor);
        }
        consumidor.accept(this);
        if (hijoDerecho != null) {
            hijoDerecho.inOrder(consumidor);
        }
    }

    /**
     * Recorre el subárbol en pre-order (raíz-izquierda-derecha).
     *
     * @param consumidor la función a aplicar a cada nodo
     */
    @Override
    public void preOrder(Consumer<TDAElemento<T>> consumidor) {
        consumidor.accept(this);
        if (hijoIzquierdo != null) {
            hijoIzquierdo.preOrder(consumidor);
        }
        if (hijoDerecho != null) {
            hijoDerecho.preOrder(consumidor);
        }
    }

    /**
     * Recorre el subárbol en post-order (izquierda-derecha-raíz).
     *
     * @param consumidor la función a aplicar a cada nodo
     */
    @Override
    public void postOrder(Consumer<TDAElemento<T>> consumidor) {
        if (hijoIzquierdo != null) {
            hijoIzquierdo.postOrder(consumidor);
        }
        if (hijoDerecho != null) {
            hijoDerecho.postOrder(consumidor);
        }
        consumidor.accept(this);
    }

    /**
     * Verifica si este nodo es una hoja (no tiene hijos).
     *
     * @return true si es hoja, false en caso contrario
     */
    @Override
    public boolean esHoja() {
        return hijoIzquierdo == null && hijoDerecho == null;
    }

    /**
     * Retorna la cantidad de nodos en el subárbol enraizado en este nodo.
     *
     * @return la cantidad de nodos
     */
    public int cantidadNodos() {
        int cantidad = 1;
        if (hijoIzquierdo != null) {
            cantidad += ((Nodo<T>) hijoIzquierdo).cantidadNodos();
        }
        if (hijoDerecho != null) {
            cantidad += ((Nodo<T>) hijoDerecho).cantidadNodos();
        }
        return cantidad;
    }

    /**
     * Retorna la cantidad de hojas (nodos sin hijos) en el subárbol.
     *
     * @return la cantidad de hojas
     */
    @Override
    public int cantidadHojas() {
        if (esHoja()) {
            return 1;
        }
        int hojas = 0;
        if (hijoIzquierdo != null) {
            hojas += hijoIzquierdo.cantidadHojas();
        }
        if (hijoDerecho != null) {
            hojas += hijoDerecho.cantidadHojas();
        }
        return hojas;
    }

    /**
     * Retorna la cantidad de nodos internos (nodos con al menos un hijo).
     *
     * @return la cantidad de nodos internos
     */
    @Override
    public int cantidadNodosInternos() {
        if (esHoja()) {
            return 0;
        }
        int internos = 1;
        if (hijoIzquierdo != null) {
            internos += hijoIzquierdo.cantidadNodosInternos();
        }
        if (hijoDerecho != null) {
            internos += hijoDerecho.cantidadNodosInternos();
        }
        return internos;
    }

    /**
     * Retorna la altura del nodo (distancia máxima a una hoja).
     *
     * @return la altura
     */
    @Override
    public int altura() {
        if (esHoja()) {
            return 0;
        }
        int alturaIzq = hijoIzquierdo != null ? hijoIzquierdo.altura() : -1;
        int alturaDer = hijoDerecho != null ? hijoDerecho.altura() : -1;
        return 1 + Math.max(alturaIzq, alturaDer);
    }

    /**
     * Obtiene el nivel (profundidad) del nodo que cumple con el criterio de búsqueda.
     *
     * @param criterioBusqueda el criterio de búsqueda
     * @return el nivel del nodo, o -1 si no se encuentra
     */
    @Override
    public int obtenerNivel(Comparable<T> criterioBusqueda) {
        int comparacion = criterioBusqueda.compareTo(dato);
        
        if (comparacion == 0) {
            return 0;
        } else if (comparacion < 0) {
            if (hijoIzquierdo != null) {
                int nivel = hijoIzquierdo.obtenerNivel(criterioBusqueda);
                return nivel >= 0 ? nivel + 1 : -1;
            }
        } else {
            if (hijoDerecho != null) {
                int nivel = hijoDerecho.obtenerNivel(criterioBusqueda);
                return nivel >= 0 ? nivel + 1 : -1;
            }
        }
        return -1;
    }
}

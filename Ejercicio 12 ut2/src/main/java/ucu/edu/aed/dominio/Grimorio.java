package ucu.edu.aed.dominio;

import java.util.ArrayList;
import java.util.List;

import ucu.edu.aed.tda.ArbolBinarioBusqueda;

/**
 * Representa el Grimorio del Archimago, que almacena todos los hechizos
 * en un Árbol Binario de Búsqueda.
 *
 * El grimorio permite:
 * - Insertar hechizos manteniéndolos ordenados por ID
 * - Consultar hechizos prohibidos (aquellos con ID impar)
 * - Generar el cántico secreto concatenando nombres de hechizos prohibidos
 */
public class Grimorio {
    private ArbolBinarioBusqueda<Hechizo> arbol;

    /**
     * Construye un grimorio vacío.
     */
    public Grimorio() {
        this.arbol = new ArbolBinarioBusqueda<>();
    }

    /**
     * Inserta un hechizo en el grimorio.
     * Los hechizos se organizan automáticamente según su ID.
     *
     * @param hechizo el hechizo a insertar
     * @return true si se insertó correctamente, false si ya existía
     */
    public boolean agregarHechizo(Hechizo hechizo) {
        return arbol.insertar(hechizo);
    }

    /**
     * PSEUDOCÓDIGO - Consultar hechizos prohibidos
     *
     * DESCRIPCIÓN:
     * Extrae todos los hechizos cuyo ID es impar del árbol binario de búsqueda
     * mediante un recorrido in-order, que retorna los elementos ordenados.
     *
     * PRECONDICIÓN:
     * - El grimorio contiene hechizos insertados en el árbol
     * - Un hechizo es prohibido si su ID es impar (id % 2 != 0)
     *
     * POSTCONDICIÓN:
     * - Se retorna una lista con todos los hechizos prohibidos ordenados por ID
     * - La lista está vacía si no hay hechizos prohibidos
     * - Los hechizos no son modificados
     *
     * PSEUDOCÓDIGO:
     * ```
     * función obtenerHechizosProhibidos():
     *     listaProhibidos ← lista vacía
     *     recorridoInOrder():
     *         para cada hechizo en el árbol (in-order):
     *             si hechizo.id es impar entonces
     *                 agregar hechizo a listaProhibidos
     *     retornar listaProhibidos
     * ```
     *
     * ANÁLISIS DE TIEMPO:
     * - O(n) donde n es la cantidad de hechizos en el árbol
     * - Se visita cada nodo una sola vez durante el recorrido in-order
     * - La verificación si un ID es impar es O(1)
     * - Insertar en la lista es O(1) amortizado
     *
     * @return lista de hechizos prohibidos ordenados por ID
     */
    public List<Hechizo> obtenerHechizosProhibidos() {
        List<Hechizo> hechizosProhibidos = new ArrayList<>();
        arbol.inOrder(hechizo -> {
            if (hechizo.esProhibido()) {
                hechizosProhibidos.add(hechizo);
            }
        });
        return hechizosProhibidos;
    }

    /**
     * PSEUDOCÓDIGO - Generar el cántico secreto
     *
     * DESCRIPCIÓN:
     * Genera un cántico secreto concatenando los nombres de todos los hechizos
     * prohibidos en orden. El recorrido es in-order del árbol, garantizando
     * que los hechizos aparezcan en orden ascendente por ID.
     *
     * PRECONDICIÓN:
     * - El grimorio contiene al menos un hechizo prohibido (ID impar)
     * - Los hechizos prohibidos tienen nombres válidos (no nulos)
     *
     * POSTCONDICIÓN:
     * - Se retorna un string con los nombres de hechizos prohibidos separados por " - "
     * - Los nombres aparecen en orden ascendente de ID
     * - Si no hay hechizos prohibidos, se retorna un string vacío
     *
     * PSEUDOCÓDIGO:
     * ```
     * función generarCantico():
     *     cantico ← string vacío
     *     primerProhibido ← verdadero
     *     recorridoInOrder():
     *         para cada hechizo en el árbol (in-order):
     *             si hechizo.id es impar entonces
     *                 si primerProhibido es falso entonces
     *                     cantico ← cantico + " - "
     *                 cantico ← cantico + hechizo.nombre
     *                 primerProhibido ← falso
     *     retornar cantico
     * ```
     *
     * ANÁLISIS DE TIEMPO:
     * - O(n) donde n es la cantidad de hechizos en el árbol
     * - Se visita cada nodo una sola vez durante el recorrido in-order
     * - Concatenación de strings: O(m) donde m es la cantidad de hechizos prohibidos
     * - Tiempo total: O(n + m) = O(n)
     *
     * @return el cántico secreto con los nombres de hechizos prohibidos
     */
    public String generarCantico() {
        StringBuilder cantico = new StringBuilder();
        final boolean[] primerProhibido = {true};

        arbol.inOrder(hechizo -> {
            if (hechizo.esProhibido()) {
                if (!primerProhibido[0]) {
                    cantico.append(" - ");
                }
                cantico.append(hechizo.getNombre());
                primerProhibido[0] = false;
            }
        });

        return cantico.toString();
    }

    /**
     * Retorna la cantidad de hechizos en el grimorio.
     */
    public int cantidadHechizos() {
        return arbol.cantidadNodos();
    }

    /**
     * Verifica si el grimorio está vacío.
     */
    public boolean estaVacio() {
        return arbol.esVacio();
    }

    /**
     * Retorna una representación en string del grimorio.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== GRIMORIO DEL ARCHIMAGO ===\n");
        sb.append("Total de hechizos: ").append(cantidadHechizos()).append("\n");
        sb.append("Hechizos en orden:\n");
        
        arbol.inOrder(hechizo -> sb.append("  ").append(hechizo).append("\n"));
        
        sb.append("\n--- HECHIZOS PROHIBIDOS ---\n");
        List<Hechizo> prohibidos = obtenerHechizosProhibidos();
        if (prohibidos.isEmpty()) {
            sb.append("  Ninguno\n");
        } else {
            prohibidos.forEach(h -> sb.append("  ").append(h).append("\n"));
        }
        
        sb.append("\n--- CÁNTICO SECRETO ---\n");
        sb.append("  ").append(generarCantico()).append("\n");
        
        return sb.toString();
    }
}

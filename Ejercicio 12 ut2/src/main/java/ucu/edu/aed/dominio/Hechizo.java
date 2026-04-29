package ucu.edu.aed.dominio;

/**
 * Representa un hechizo del Grimorio del Archimago.
 * Cada hechizo tiene un ID único (entero positivo) y un nombre.
 */
public class Hechizo implements Comparable<Hechizo> {
    private int id;
    private String nombre;

    /**
     * Construye un hechizo con el ID y nombre dados.
     * @param id el identificador único del hechizo (entero positivo)
     * @param nombre el nombre del hechizo
     */
    public Hechizo(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    /**
     * Retorna el ID del hechizo.
     */
    public int getId() {
        return id;
    }

    /**
     * Retorna el nombre del hechizo.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Compara este hechizo con otro basándose en el ID.
     */
    @Override
    public int compareTo(Hechizo otro) {
        return Integer.compare(this.id, otro.id);
    }

    /**
     * Verifica si este hechizo es prohibido (ID impar).
     * @return true si el ID es impar, false si es par
     */
    public boolean esProhibido() {
        return id % 2 != 0;
    }

    @Override
    public String toString() {
        return nombre + " (ID: " + id + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Hechizo hechizo = (Hechizo) obj;
        return id == hechizo.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}

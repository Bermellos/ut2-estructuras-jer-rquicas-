package ucu.edu.aed;

import ucu.edu.aed.dominio.Grimorio;
import ucu.edu.aed.dominio.Hechizo;

/**
 * Programa principal que demuestra el funcionamiento del Grimorio del Archimago.
 * Inserta los hechizos especificados en el orden dado y muestra:
 * - Todos los hechizos ordenados
 * - Los hechizos prohibidos (ID impar)
 * - El cántico secreto
 */
public class Main {
    public static void main(String[] args) {
        // Crear el grimorio
        Grimorio grimorio = new Grimorio();

        // Insertar los hechizos en el orden especificado
        System.out.println("Insertando hechizos en el grimorio...\n");
        
        int[] ids = {42, 17, 58, 9, 31, 73, 25, 50, 65, 88};
        String[] nombres = {
            "Fireball", "Ice Lance", "Thunder", "Invisibility", "Levitate",
            "Summon", "Heal", "Teleport", "Shield", "Curse"
        };
        
        for (int i = 0; i < ids.length; i++) {
            Hechizo h = new Hechizo(ids[i], nombres[i]);
            grimorio.agregarHechizo(h);
            System.out.println("✓ Insertado: " + h);
        }

        // Mostrar el estado del grimorio
        System.out.println("\n" + grimorio);
    }
}

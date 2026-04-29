package com.example;

public class RegistroNave {
    AVLImpl<Nave> registro;                  // O(1)
    ListaEnlazada<Nave> explorador;          // O(1)

    public RegistroNave(){
        registro = new AVLImpl<>();          // O(1)
        explorador = new ListaEnlazada<>();  // O(1)
    }

    // agregarNave:
    // O(1) + O(log n) = O(log n)
    public void agregarNave(int codigo, String clase, int cantidadCombustible){
        Nave n1 = new Nave(codigo, clase, cantidadCombustible); // O(1)

        registro.insertar(n1);              // O(log n)
    }

    // buscarNaveExplorador:
    // O(n) * (O(1) + O(1) + O(1)) = O(n)
    public void buscarNaveExplorador(){

        registro.inOrder(nave -> {          // O(n)
            if (nave.getClase().equalsIgnoreCase("Explorador")){ // O(1)
                explorador.agregar(nave);   // O(1)
                System.out.println(nave.getCodigo() + " - " + nave.getClase() +" - " + nave.getCantidadCombustible() ); // O(1)
            }
            
        });
    }
    
    // calculoCombustible:
    // O(1) + O(1) + O(k) * O(1) + O(1) = O(k) ≤ O(n)
    public int calculoCombustible(){

        int divisor = explorador.tamano();  // O(1)
        
        // Se usa un array como contenedor mutable, ya que las variables en lambdas deben ser finales.
        final int[] total = {0};            // O(1)

        explorador.recorrer(nave -> {      // O(k)
            total[0] += nave.getCantidadCombustible(); // O(1)
        });
        return total[0]/divisor;           // O(1)
    }

    // total:
    // n * O(log n) + O(n) + O(n)
    // = O(n log n) + O(n) + O(n)
    // = O(n log n)
}
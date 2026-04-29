package com.example;

public class App 
{
    public static void main( String[] args )
    {
        RegistroNave rn = new RegistroNave();

        rn.agregarNave(10, "Explorador", 0);
        rn.agregarNave(20, "Destructor", 90);
        rn.agregarNave(30, "Médica", 100);
        rn.agregarNave(40, "Explorador", 50);
        rn.agregarNave(50, "Carguero", 20);
        rn.agregarNave(60, "Destructor", 28);
        rn.agregarNave(70, "Explorador", 14);
        rn.agregarNave(80, "Médica", 7);
        rn.agregarNave(90, "Carguero", 23);
        rn.agregarNave(100, "Explorador", 26);

        rn.buscarNaveExplorador();
        System.out.println(rn.calculoCombustible());

    }
}

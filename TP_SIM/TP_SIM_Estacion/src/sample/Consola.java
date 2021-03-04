package sample;

import java.io.IOException;
import java.util.Scanner;

public class Consola {
    public static void main(String[] args) throws IOException {

        String op = " ";
        while(!(op.equals("0"))){
            Scanner input = new Scanner(System.in);
            System.out.println("Ingrese la cantidad de simulaciones: ");
            int cantSimulaciones = input.nextInt();
            System.out.println("Desde: ");
            int desde = input.nextInt();
            System.out.println("Hasta: ");
            int cantFilas = input.nextInt();

            Simulacion2 sim = new Simulacion2(cantSimulaciones,0,desde,cantFilas);

            System.out.println("Desea continuar?: ");
            op = input.next();
        }

    }
}

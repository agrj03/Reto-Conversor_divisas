package principal;

import modelos.ConsultaDivisa;
import modelos.Moneda;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class principal {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double monto = 0;
        int opcion = 0;
        boolean ejecutar = true;
        ConsultaDivisa con = new ConsultaDivisa();
        List<Moneda> lista = new ArrayList<>();

        while (ejecutar){
            String base = "", target = "";

            System.out.println("""
                    \s*******************************************
                    CONVERSOR DE MONEDAS
                   \s
                    ----OPCIONES DISPONIBLES----
                    1. Dólares a Pesos Mexícanos
                    2. Pesos Mexícanos a Dólares\s
                    3. Dólares a Euros
                    4. Euros a Dólares\s
                    5. Pesos a Yenes (JP)
                    6. Yenes a Pesos
                    7. Pesos a Euros
                    8. Euros a Pesos
                   
                    0. S A L I R
                    *******************************************""");
            try {
                System.out.println("Elije una opción:");
                opcion = Integer.parseInt( input.nextLine());
                System.out.println("opci "+opcion);
            }catch (NumberFormatException e){
                System.out.println("Introduzca un número. " +e.getMessage());
            }

            System.out.println("opcion "+opcion);

            if (opcion >= 9 || opcion < 0){
                System.out.println("Opción no válida, terminando el programa!");
                break;
            }else{
                System.out.println("Monto a convertir: ");
                try {
                    monto = Double.parseDouble(input.nextLine());
                }catch (NumberFormatException e){
                    System.out.println("Introduzca un número. " +e.getMessage());
                }
                System.out.println("Monto: "+monto);
            }

            switch (opcion) {
                case 1:
                    base = "USD";
                    target = "MXN";
                    break;
                case 2:
                    base = "MXN";
                    target = "USD";
                    break;
                case 3:
                    base = "USD";
                    target = "EUR";
                    break;
                case 4:
                    base = "EUR";
                    target = "USD";
                    break;
                case 5:
                    base = "MXN";
                    target = "JPY";
                    break;
                case 6:
                    base = "JPY";
                    target = "MXN";
                    break;
                case 7:
                    base = "MXN";
                    target = "EUR";
                    break;
                case 8:
                    base = "EUR";
                    target = "MXN";
                    break;
                case 0:
                    System.out.println("Finalizando el programa, gracias por utilizarlo.");
                    ejecutar=false;
                    break;
            }
            if (monto>0){
                try {
                    Moneda dinero = con.conectar(base, target , monto);
                    lista.add(dinero);
                    System.out.printf("$%.2f %s equivalen a un total de $%.2f %s", monto, base, dinero.conversion_result(), target);
                } catch (Exception e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
        }
        System.out.println("Conversiones totales: "+ lista.size());
        System.out.println("Valores obtenidos: "+ lista.toString());






    }

}

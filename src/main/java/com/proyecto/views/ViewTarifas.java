package com.proyecto.views;

import com.proyecto.repositories.models.Tarifas;

public class ViewTarifas extends ViewMain{
    public static void startMenu(){
        int op = 0;

        do {
            op = mostrarMenu();

            switch (op) {
                case 1:
                    crearTarifa();
                    break;
                case 2:
                    listarTarifas();
                    break;
                default:
                    System.out.println("Opcion invalida.");
                    break;
            }
        } while (op >= 1 && op < 5);
    }
    
    public static void listarTarifas() {
        System.out.println("Lista de Tarifas");
        for (Tarifas tarifa : serviceTarifas.listar()) {
            tarifa.imprimirTarifas();
            System.out.println();
        }
    }

    public static void crearTarifa() {
        scanner.nextLine();
        System.out.print("Ingrese el valor del credito: ");
        String valorCredito = scanner.nextLine();
        Tarifas tarifa = new Tarifas(valorCredito);
        serviceTarifas.crear(tarifa);;
    }

    public static int mostrarMenu(){
        System.out.println("###MENU TARIFA###");
        System.out.println("1. Crear tarifa");
        System.out.println("3. Listar tarifas");
        System.out.println("5. Salir");
        return scanner.nextInt();
    }
}

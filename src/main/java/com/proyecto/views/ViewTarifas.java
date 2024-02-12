package com.proyecto.views;

import com.proyecto.repositories.models.Tarifas;
import com.proyecto.repositories.models.Programas;

import java.util.List;

import com.proyecto.repositories.models.Periodos;

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
                case 3:
                    listarTarifasPorPeriodo();
                    break;
                case 4:
                    listarTarifasPorPrograma();
                    break;
                default:
                    System.out.println("Opcion invalida.");
                    break;
            }
        } while (op >= 1 && op < 5);
    }
    
    public static void listarTarifas() {
        System.out.println("Lista de Tarifas");
        for (Tarifas tarifa : serviceTarifas.listarConPeriodos()) {
            tarifa.imprimirTarifas();
            System.out.println();
        }
    }

    public static void listarTarifasPorPeriodo() {
        System.out.println("Lista de Tarifas por Periodo");
        Periodos periodo = obtenerPeriodoSeleccionado();
        if (periodo != null) {
            for (Tarifas tarifa : serviceTarifas.buscarPorPeriodo(periodo)) {
                tarifa.imprimirTarifas();
                System.out.println();
            }
        } else {
            System.out.println("No se ha seleccionado un período válido.");
        }
    }
    
    public static void listarTarifasPorPrograma() {
        System.out.println("Lista de Tarifas por Programa");
        Programas programa = obtenerProgramaSeleccionado();
        if (programa != null) {
            for (Tarifas tarifa : serviceTarifas.buscarPorPrograma(programa)) {
                tarifa.imprimirTarifas();
                System.out.println();
            }
        } else {
            System.out.println("No se ha seleccionado un programa válido.");
        }
    }

    public static void crearTarifa() {
        scanner.nextLine();
        System.out.print("Ingrese el valor del crédito: ");
        String valorCredito = scanner.nextLine();
        
        Periodos periodo = obtenerPeriodoSeleccionado();
        if (periodo == null) {
            System.out.println("No se ha seleccionado un período válido.");
            return;
        }
        
        Programas programa = obtenerProgramaSeleccionado();
        if (programa == null) {
            System.out.println("No se ha seleccionado un programa válido.");
            return;
        }
        
        Tarifas tarifa = new Tarifas(programa, periodo, valorCredito);
        tarifa.setPrograma(programa);
        tarifa.setPeriodo(periodo);
        serviceTarifas.crear(tarifa);
    }
    
    private static Periodos obtenerPeriodoSeleccionado() {
        System.out.println("Seleccione un período:");
        List<Periodos> periodos = servicePeriodos.listar();
        for (Periodos periodo : periodos) {
            System.out.println(periodo.getId_periodo() + ". " + periodo.getCodPeriodo());
        }
        System.out.print("Ingrese el número del período seleccionado: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();
        if (opcion >= 1 && opcion <= periodos.size()) {
            return periodos.get(opcion - 1); 
        } else {
            System.out.println("Opción inválida. Inténtelo de nuevo.");
            return null;
        }
    }
    
    private static Programas obtenerProgramaSeleccionado() {
        System.out.println("Seleccione un programa:");
        List<Programas> programas = serviceProgramas.listar();
        for (Programas programa : programas) {
            System.out.println(programa.getId_programa() + ". " + programa.getNomPrograma());
        }
        System.out.print("Ingrese el número del programa seleccionado: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();
        if (opcion >= 1 && opcion <= programas.size()) {
            return programas.get(opcion - 1); 
        } else {
            System.out.println("Opción inválida. Inténtelo de nuevo.");
            return null;
        }
    }
    
    public static int mostrarMenu(){
        System.out.println("\u001B[36m╔════════════════════════════════════╗\u001B[0m");
        System.out.println("\u001B[36m║     \u001B[0m\u001B[33m       MENU TARIFA \u001B[36m            ║\u001B[0m");
        System.out.println("\u001B[36m╠════════════════════════════════════╣\u001B[0m");
        System.out.println("\u001B[36m║     \u001B[0m\u001B[33m1. Crear tarifa\u001B[36m                ║\u001B[0m");
        System.out.println("\u001B[36m║     \u001B[0m\u001B[33m2. Listar todas las tarifas\u001B[36m    ║\u001B[0m");
        System.out.println("\u001B[36m║     \u001B[0m\u001B[33m3. Listar tarifas por período\u001B[36m  ║\u001B[0m");
        System.out.println("\u001B[36m║     \u001B[0m\u001B[33m4. Listar tarifas por programa\u001B[36m ║\u001B[0m");
        System.out.println("\u001B[36m║     \u001B[0m\u001B[33m5. Salir\u001B[36m                       ║\u001B[0m");
        System.out.println("\u001B[36m╚════════════════════════════════════╝\u001B[0m");
        System.out.print("\u001B[33m --> \u001B[0m");
        return scanner.nextInt();
    }
    
    
}

package com.proyecto.views;


import com.proyecto.exceptiones.periodosexceptions.PeriodosNullException;
import com.proyecto.repositories.models.Periodos;

public class ViewPeriodos extends ViewMain{
    public static void startMenu(){
        int op = 0;

        do {
            op = mostrarMenu();

            switch (op) {
                case 1:
                    crearPeriodo();
                    break;
                case 2:
                    buscarPeriodo();
                    break;
                case 3:
                    listarPeriodos();
                    break;
                case 4:
                    eliminarPeriodo();
                default:
                    System.out.println("Opcion invalida.");
                    break;
            }
        } while (op >= 1 && op < 5);
    }   

    public static void listarPeriodos() {
        System.out.println("Lista de Periodos");
        for (Periodos periodo : servicePeriodos.listar()) {
            periodo.imprimirPeriodo();
            System.out.println();
        }
    }

        public static Periodos buscarGetPeriodo() {
        System.out.println("Busqueda de Periodo "); 
        scanner.nextLine();       
        System.out.print("Codigo del periodo: ");
        String codPeriodo = scanner.nextLine();

        try {
            return servicePeriodos.buscar(codPeriodo);

        } catch (PeriodosNullException e) {

            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void buscarPeriodo(){
        System.out.println("Buscar Periodo");
        scanner.nextLine();
        System.out.println("Codigo del periodo: ");
        String codPeriodo = scanner.nextLine();

        try{
            Periodos periodo = servicePeriodos.buscar(codPeriodo);
            System.out.println();
            periodo.imprimirPeriodo();
        }catch(PeriodosNullException e){
            System.out.println(e.getMessage());
        }
    }

    public static void crearPeriodo() {
        scanner.nextLine();
        System.out.print("Año: ");
        String año = scanner.nextLine();
        System.out.print("Semestre: ");
        String semestre = scanner.nextLine();
        Periodos periodo = new Periodos(año, semestre);
        servicePeriodos.crear(periodo);
    }

    public static void eliminarPeriodo() {
        Periodos periodo = buscarGetPeriodo();
        if (periodo != null) {
            servicePeriodos.eliminar(periodo);
            System.out.println("Elmininado el periodo con exito");
        } else {
            System.out.println("Se presentó un problema y no se puedo eliminar el periodo");
        }

    }

    public static int mostrarMenu(){
        System.out.println("\u001B[36m╔═══════════════════════════════════╗");
        System.out.println("║\u001B[35m          MENU DE PERIODO          \u001B[36m║");
        System.out.println("╠═══════════════════════════════════╣");
        System.out.println("║ \u001B[33m1. Crear periodo                  \u001B[36m║");
        System.out.println("║ \u001B[33m2. Buscar periodo                 \u001B[36m║");
        System.out.println("║ \u001B[33m3. Listar periodos                \u001B[36m║");
        System.out.println("║ \u001B[33m4. Eliminar periodo               \u001B[36m║");
        System.out.println("║ \u001B[33m5. Salir                          \u001B[36m║");
        System.out.println("╚═══════════════════════════════════╝\u001B[0m");
        System.out.print("\u001B[33m --> \u001B[0m");
        return scanner.nextInt();
    }
    
}

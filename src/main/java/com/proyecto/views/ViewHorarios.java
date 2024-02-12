package com.proyecto.views;

import com.proyecto.exceptiones.horariosexceptions.HorariosNullException;
import com.proyecto.repositories.models.Horarios;

public class ViewHorarios extends ViewMain {
    public static void startMenu(){
        int op = 0;

        do {
            op = mostrarMenu();

            switch (op) {
                case 1:
                    crearHorario();
                    break;
                case 2:
                    buscarHorario();
                    break;
                case 3:
                    listarHorarios();
                    break;
                case 4:
                    elminarHorario();
                    break;
                default:
                    System.out.println("Opcion invalida.");
                    break;
            }
        } while (op >= 1 && op < 5);
    }

     public static void listarHorarios() {
        System.out.println("Lista de Horarios");
        for (Horarios horario : serviceHorarios.listar()) {
            horario.imprimirHorario();
            System.out.println();
        }
    }

        public static Horarios buscarGetHorario() {
        System.out.println("Busqueda de horario "); 
        scanner.nextLine();       
        System.out.print("Dia: ");
        String dia = scanner.nextLine();

        try {
            return serviceHorarios.buscar(dia);

        } catch (HorariosNullException e) {

            System.out.println(e.getMessage());
            return null;
        }
    }

    
    public static void buscarHorario(){
        System.out.println("Buscar horario");
        scanner.nextLine();
        System.out.println("Dia: ");
        String dia = scanner.nextLine();

        try{
            Horarios horario = serviceHorarios.buscar(dia);
            System.out.println();
            horario.imprimirHorario();;
        }catch(HorariosNullException e){
            System.out.println(e.getMessage());
        }
    }

    public static void crearHorario() {
        scanner.nextLine();
        System.out.print("Dia: ");
        String dia = scanner.nextLine();
        System.out.print("Hora Inicio: ");
        String horaIni = scanner.nextLine();
        System.out.print("Hora Fin: ");
        String horaFin = scanner.nextLine();
        Horarios horario = new Horarios(dia, horaIni, horaFin);
        serviceHorarios.crear(horario);
    }

    public static void elminarHorario() {
        Horarios horario = buscarGetHorario();
        if (horario != null) {
            serviceHorarios.eliminar(horario);
            System.out.println("Elmininado el horario con exito");
        } else {
            System.out.println("Se presentó un problema y no se puedo eliminar el horario");
        }

    }

    public static int mostrarMenu() {
        System.out.println("\u001B[36m╔════════════════════════════════════╗\u001B[0m");
        System.out.println("\u001B[36m║            MENU HORARIO            ║\u001B[0m");
        System.out.println("\u001B[36m╠════════════════════════════════════╣\u001B[0m");
        System.out.println("\u001B[36m║ 1. Crear horario                  ║\u001B[0m");
        System.out.println("\u001B[36m║ 2. Buscar horario                 ║\u001B[0m");
        System.out.println("\u001B[36m║ 3. Listar horario                 ║\u001B[0m");
        System.out.println("\u001B[36m║ 4. Eliminar horario               ║\u001B[0m");
        System.out.println("\u001B[36m║ 5. Salir                          ║\u001B[0m");
        System.out.println("\u001B[36m╚════════════════════════════════════╝\u001B[0m");
        System.out.print("\u001B[33m --> \u001B[0m");
        return scanner.nextInt();
    }
    
}

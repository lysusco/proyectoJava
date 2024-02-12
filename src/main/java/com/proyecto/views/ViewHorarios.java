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

    public static int mostrarMenu(){
        System.out.println("###MENU HORARIO###");
        System.out.println("1. Crear horario");
        System.out.println("2. Buscar horario");
        System.out.println("3. Listar horario");
        System.out.println("4. Eliminar horario");
        System.out.println("5. Salir");
        return scanner.nextInt();
    }
}

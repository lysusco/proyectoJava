package com.proyecto.views;

import com.proyecto.exceptiones.asignaturasexceptions.AsignaturaNullExceptions;
import com.proyecto.repositories.models.Asignaturas;

public class ViewAsignaturas extends ViewMain{
    public static void startMenu(){
        int op = 0;

        do {
            op = mostrarMenu();

            switch (op) {
                case 1:
                    crearAsignatura();
                    break;
                case 2:
                    buscarAsignatura();
                    break;
                case 3:
                    listarAsignaturas();
                    break;
                case 4:
                    eliminarAsignatura();
                default:
                    System.out.println("Opcion invalida.");
                    break;
            }
        } while (op >= 1 && op < 5);
    }

    public static void listarAsignaturas() {
        System.out.println("Lista de asignaturas");
        for (Asignaturas asignatura : serviceAsignatura.listar()) {
            asignatura.imprimirAsignatura();
            System.out.println();
        }
    }


    public static Asignaturas buscarGetAsignatura() {
        System.out.println("Busqueda de Asignatura "); 
        scanner.nextLine();       
        System.out.print("Nombre: ");
        String nomAsignatura = scanner.nextLine();

        try {
            return serviceAsignatura.buscar(nomAsignatura);

        } catch (AsignaturaNullExceptions e) {

            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void buscarAsignatura(){
        System.out.println("Buscar asignatura");
        scanner.nextLine();
        System.out.println("Nombre asignatura: ");
        String nomAsignatura = scanner.nextLine();

        try{
            Asignaturas asignatura = serviceAsignatura.buscar(nomAsignatura);
            System.out.println();
            asignatura.imprimirAsignatura();
        }catch(AsignaturaNullExceptions e){
            System.out.println(e.getMessage());
        }
    }

    public static void crearAsignatura() {
        scanner.nextLine();
        System.out.print("Nombre de la asignatura: ");
        String nomAsignatura = scanner.nextLine();
        System.out.print("Cupo de la asignatura: ");
        String cupoAsignatura = scanner.nextLine();
        System.out.print("Creditos: ");
        String creditos = scanner.nextLine();
        Asignaturas asignatura = new Asignaturas(nomAsignatura, cupoAsignatura, creditos);
        serviceAsignatura.crear(asignatura);
    }

    public static void eliminarAsignatura() {
        Asignaturas asignatura = buscarGetAsignatura();
        if (asignatura != null) {
            serviceAsignatura.eliminar(asignatura);
            System.out.println("Elmininado el asignatura con exito");
        } else {
            System.out.println("Se presentó un problema y no se puedo eliminar el asignatura");
        }

    }

    
    public static int mostrarMenu(){
        System.out.println("###MENU ASIGNATURA###");
        System.out.println("1. Crear asignatura");
        System.out.println("2. Buscar asignatura");
        System.out.println("3. Listar asignaturas");
        System.out.println("4. Eliminar asignatura");
        System.out.println("5. Salir");
        return scanner.nextInt();
    }
}

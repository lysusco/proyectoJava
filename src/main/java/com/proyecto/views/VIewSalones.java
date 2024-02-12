package com.proyecto.views;

import com.proyecto.exceptiones.salonesexceptions.SalonesNullException;
import com.proyecto.repositories.models.Salones;

public class ViewSalones extends ViewMain{
    public static void startMenu(){
        int op = 0;

        do {
            op = mostrarMenu();

            switch (op) {
                case 1:
                    crearSalon();
                    break;
                case 2:
                    buscarSalon();
                    break;
                case 3:
                    listarSalones();
                    break;
                case 4:
                    eliminarSalon();
                default:
                    System.out.println("Opcion invalida.");
                    break;
            }
        } while (op >= 1 && op < 5);
    }

    public static void listarSalones() {
        System.out.println("Lista de Alumnos");
        for (Salones salon : serviceSalones.listar()) {
            salon.imprimirSalon();
            System.out.println();
        }
    }

    public static Salones buscaGetSalon() {
        System.out.println("Busqueda de Salon "); 
        scanner.nextLine();       
        System.out.print("Referencia: ");
        String referenciaSalon = scanner.nextLine();

        try {
            return serviceSalones.buscar(referenciaSalon);

        } catch (SalonesNullException e) {

            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void buscarSalon(){
        System.out.println("Buscar Salon");
        scanner.nextLine();
        System.out.println("Documento: ");
        String referenciaSalon = scanner.nextLine();

        try{
            Salones salon = serviceSalones.buscar(referenciaSalon);
            System.out.println();
            salon.imprimirSalon();
        }catch(SalonesNullException e){
            System.out.println(e.getMessage());
        }
    }

    public static void crearSalon() {
        scanner.nextLine();
        System.out.print("Referencia: ");
        String referenciaSalon = scanner.nextLine();
        System.out.print("Cupo: ");
        String cupoSalon = scanner.nextLine();
        System.out.print("Ubicacion: ");
        String ubicacion = scanner.nextLine();
        Salones salon = new Salones(referenciaSalon, cupoSalon, ubicacion);
        serviceSalones.crear(salon);
    }

    public static void eliminarSalon() {
        Salones salon = buscaGetSalon();
        if (salon != null) {
            serviceSalones.eliminar(salon);
            System.out.println("Elmininado el salon con exito");
        } else {
            System.out.println("Se presentó un problema y no se puedo eliminar el salon");
        }

    }

    public static int mostrarMenu(){
        System.out.println("###MENU SALON###");
        System.out.println("1. Crear salon");
        System.out.println("2. Buscar salon");
        System.out.println("3. Listar salon");
        System.out.println("4. Eliminar salon");
        System.out.println("5. Salir");
        return scanner.nextInt();
    }

}

package com.proyecto.views;


import com.proyecto.exceptiones.programasexceptions.ProgramasNullException;
import com.proyecto.repositories.models.Programas;

public class ViewProgramas extends ViewMain{
    public static void startMenu(){
        int op = 0;

        do {
            op = mostrarMenu();

            switch (op) {
                case 1:
                    crearPrograma();
                    break;
                case 2:
                    buscarPrograma();
                    break;
                case 3:
                    listarProgramas();
                    break;
                case 4:
                    eliminarPrograma();
                default:
                    System.out.println("Opcion invalida.");
                    break;
            }
        } while (op >= 1 && op < 5);
    }

     public static void listarProgramas() {
        System.out.println("Lista de Programas");
        for (Programas programa : serviceProgramas.listar()) {
            programa.imprimirPrograma();
            System.out.println();
        }
    }

    public static Programas buscarGetPrograma() {
        System.out.println("Busqueda de programa "); 
        scanner.nextLine();       
        System.out.print("Nombre del programa: ");
        String nomPrograma = scanner.nextLine();

        try {
            return serviceProgramas.buscar(nomPrograma);

        } catch (ProgramasNullException e) {

            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void buscarPrograma(){
        System.out.println("Buscar programa");
        scanner.nextLine();
        System.out.println("Nombre del programa: ");
        String nomPrograma = scanner.nextLine();

        try{
            Programas programa = serviceProgramas.buscar(nomPrograma);
            System.out.println();
            programa.imprimirPrograma();
        }catch(ProgramasNullException e){
            System.out.println(e.getMessage());
        }
    }

    public static void crearPrograma() {
        scanner.nextLine();
        System.out.print("Nombre del programa: ");
        String nomPrograma = scanner.nextLine();
        System.out.print("Nivel del programa: ");
        String nivel = scanner.nextLine();
        Programas programa = new Programas(nomPrograma, nivel);
        serviceProgramas.crear(programa);
    }

    public static void eliminarPrograma() {
        Programas programa = buscarGetPrograma();
        if (programa != null) {
            serviceProgramas.eliminar(programa);
            System.out.println("Elmininado el programa con exito");
        } else {
            System.out.println("Se presentó un problema y no se puedo eliminar el programa");
        }
    }
    public static int mostrarMenu(){
        System.out.println("###MENU PROGRAMAS###");
        System.out.println("1. Crear programa");
        System.out.println("2. Buscar programa");
        System.out.println("3. Listar programas");
        System.out.println("4. Eliminar programa");
        System.out.println("5. Salir");
        return scanner.nextInt();
    }
}

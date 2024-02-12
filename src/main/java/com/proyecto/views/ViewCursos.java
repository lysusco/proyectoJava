package com.proyecto.views;

import com.proyecto.exceptiones.cursosexceptions.CursosNullException;
import com.proyecto.repositories.models.Cursos;

public class ViewCursos extends ViewMain{
    public static void startMenu(){
        int op = 0;

        do {
            op = mostrarMenu();

            switch (op) {
                case 1:
                    crearCurso();
                    break;
                case 2:
                    listarCursos();
                    break;
                case 3:
                    eliminarCurso();
                default:
                    System.out.println("Opcion invalida.");
                    break;
            }
        } while (op >= 1 && op < 4);
    }

        public static void listarCursos() {
        System.out.println("Lista de Cursos");
        for (Cursos curso : serviceCursos.listar()) {
            curso.imprimirCurso();
            System.out.println();
        }
    }


        public static Cursos buscarGetCurso() {
        System.out.println("Busqueda de curso "); 
        scanner.nextLine();       
        System.out.print("Nombre: ");
        String nomCurso = scanner.nextLine();

        try {
            return serviceCursos.buscar(nomCurso);

        } catch (CursosNullException e) {

            System.out.println(e.getMessage());
            return null;
        }
    }


    public static void crearCurso() {
        scanner.nextLine();
        System.out.print("Nombre Curso: ");
        String tipoDocumento = scanner.nextLine();
        System.out.print("Guia Catedra: ");
        String numDocumento = scanner.nextLine();
        Cursos curso = new Cursos(tipoDocumento, numDocumento);
        serviceCursos.crear(curso);
    }

        public static void eliminarCurso() {
        Cursos curso = buscarGetCurso();
        if (curso != null) {
            serviceCursos.eliminar(curso);
            System.out.println("Elmininado el curso con exito");
        } else {
            System.out.println("Se presentó un problema y no se puedo eliminar el curso");
        }

    }

    public static int mostrarMenu() {
        System.out.println("\u001B[36m╔════════════════════════════════════╗\u001B[0m");
        System.out.println("\u001B[36m║            MENU CURSOS             ║\u001B[0m");
        System.out.println("\u001B[36m╠════════════════════════════════════╣\u001B[0m");
        System.out.println("\u001B[36m║ 1. Crear curso                     ║\u001B[0m");
        System.out.println("\u001B[36m║ 2. Listar cursos                   ║\u001B[0m");
        System.out.println("\u001B[36m║ 3. Eliminar curso                  ║\u001B[0m");
        System.out.println("\u001B[36m║ 4. Salir                           ║\u001B[0m");
        System.out.println("\u001B[36m╚════════════════════════════════════╝\u001B[0m");
        System.out.print("\u001B[33m --> \u001B[0m");
        return scanner.nextInt();
    }
    
}

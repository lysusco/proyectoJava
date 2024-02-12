package com.proyecto.views;

import com.proyecto.exceptiones.alumnosexceptions.AlumnosNullException;
import com.proyecto.exceptiones.departamentosexceptions.DepartamentosNullException;
import com.proyecto.repositories.models.Departamentos;
import com.proyecto.repositories.models.Profesores;

public class ViewDepartamentos extends ViewMain {
    public static void startMenu() {
        int op = 0;

        do {
            op = mostrarMenu();

            switch (op) {
                case 1:
                    crearDepartamento();
                    break;
                case 2:
                    buscarDepartamento();
                    break;
                case 3:
                    listarDepartamentos();
                    break;
                case 4:
                    eliminarDepartamento();
                    break;
                case 5:
                    obtenerDepartamentoPorProfesor();
                    break;
                case 6:
                    System.out.println("Saliendo del menú de departamentos...");
                    break;
                default:
                    System.out.println("Opcion invalida.");
                    break;
            }
        } while (op >= 1 && op < 6);
    }

    public static void listarDepartamentos() {
        System.out.println("Lista de Departamentos");
        for (Departamentos departamento : serviceDepartamentos.listar()) {
            departamento.imprimirDepartamento();
            System.out.println();
        }
    }

    public static Departamentos buscarGetDepartamentos() {
        System.out.println("Busqueda del Departamento ");
        scanner.nextLine();
        System.out.print("Nombre: ");
        String nomDepartamento = scanner.nextLine();

        try {
            return serviceDepartamentos.buscar(nomDepartamento);

        } catch (DepartamentosNullException e) {

            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void buscarDepartamento() {
        System.out.println("Buscar departamento");
        scanner.nextLine();
        System.out.println("Nombre: ");
        String nomDepartamento = scanner.nextLine();

        try {
            Departamentos departmento = serviceDepartamentos.buscar(nomDepartamento);
            System.out.println();
            departmento.imprimirDepartamento();
        } catch (DepartamentosNullException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void crearDepartamento() {
        scanner.nextLine();
        System.out.print("Nombre del departamento: ");
        String nomDepartamento = scanner.nextLine();
        Departamentos departamento = new Departamentos(nomDepartamento);
        serviceDepartamentos.crear(departamento);
    }

    public static void eliminarDepartamento() {
        Departamentos departamento = buscarGetDepartamentos();
        if (departamento != null) {
            serviceDepartamentos.eliminar(departamento);
            System.out.println("Elmininado el departamento con exito");
        } else {
            System.out.println("Se presentó un problema y no se puedo eliminar el departamento");
        }

    }

    private static Profesores buscarGetProfesor() {
        scanner.nextLine();
        System.out.print("Documento del profesor: ");
        String numDocumento = scanner.nextLine();

        try {
            return serviceProfesores.buscar(numDocumento);
        } catch (AlumnosNullException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


    public static void obtenerDepartamentoPorProfesor() {
        Profesores profesor = buscarGetProfesor();
        if (profesor != null) {
            Departamentos departamento = serviceDepartamentos.obtenerDepartamentoPorProfesor(profesor);
            if (departamento != null) {
                System.out.println("El profesor pertenece al departamento " + departamento.getNomDepartamento());
            } else {
                System.out.println("El profesor no está asignado a ningún departamento.");
            }
        } else {
            System.out.println("No se encontró el profesor");
        }
    }

    public static int mostrarMenu() {
        System.out.println("\u001B[34m╔══════════════════════════════════════╗");
        System.out.println("║\u001B[30m        MENU DEPARTAMENTO             \u001B[34m║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("\u001B[33m║ 1. Crear departamento                ║");
        System.out.println("║ 2. Buscar departamento               ║");
        System.out.println("║ 3. Listar departamento               ║");
        System.out.println("║ 4. Eliminar departamento             ║");
        System.out.println("║ 5. Obtener departamento por profesor ║");
        System.out.println("║ 6. Salir                             ║");
        System.out.println("╚══════════════════════════════════════╝\u001B[0m");
        System.out.print("\u001B[33m --> \u001B[0m");
        return scanner.nextInt();
    }
    
}

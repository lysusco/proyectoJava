package com.proyecto.views;

import java.util.List;

import com.proyecto.exceptiones.alumnosexceptions.AlumnosNullException;
import com.proyecto.repositories.models.Departamentos;
import com.proyecto.repositories.models.Profesores;

public class ViewProfesores extends ViewMain {
    public static void startMenu() {
        int op = 0;

        do {
            op = mostrarMenu();

            switch (op) {
                case 1:
                    crearProfesores();
                    break;
                case 2:
                    buscarProfesores();
                    break;
                case 3:
                    listarProfesores();
                    break;
                case 4:
                    eliminarProfesores();
                    break;
                case 5:
                    asignarDepartamento();
                    break;
                case 6:
                    obtenerProfesoresPorDepartamento();
                    break;
                case 7:
                    System.out.println("Saliendo del menú de profesores...");
                    break;
                default:
                    System.out.println("Opcion invalida.");
                    break;
            }
        } while (op >= 1 && op < 7);
    }

    public static void listarProfesores() {
        System.out.println("Lista de Clientes");
        for (Profesores profesor : serviceProfesores.listar()) {
            profesor.imprimirProfesor();
            System.out.println();
        }
    }

    public static Profesores buscarGetProfesor() {
        System.out.println("Busqueda de Profesor ");

        List<Profesores> profesores = serviceProfesores.listar();
        if (profesores.isEmpty()) {
            System.out.println("No hay profesores registrados.");
            return null;
        }

        System.out.println("Lista de Profesores:");
        for (Profesores profesor : profesores) {
            System.out.println("Documento: " + profesor.getNumDocumento() + ", Nombre: " + profesor.getNombreCompleto());
        }
  
        System.out.print("Ingrese el documento del profesor: ");
        String numDocumento = scanner.nextLine();
    

        try {
            return serviceProfesores.buscar(numDocumento);
        } catch (AlumnosNullException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public static void listarTodosLosProfesores() {
        System.out.println("Lista de Todos los Profesores:");
        List<Profesores> profesores = serviceProfesores.listar();
        for (Profesores profesor : profesores) {
            profesor.imprimirProfesor();
            System.out.println();
        }
    }
    
    public static void buscarProfesores() {
        listarTodosLosProfesores();
        System.out.println("Buscar profesor");
        scanner.nextLine();
        System.out.println("Documento: ");
        String numDocumento = scanner.nextLine();
    
        try {
            Profesores profesor = serviceProfesores.buscar(numDocumento);
            System.out.println();
            profesor.imprimirProfesor();
        } catch (AlumnosNullException e) {
            System.out.println(e.getMessage());
        }
    }
    

    public static void crearProfesores() {
        scanner.nextLine();
        System.out.print("Tipo de documento: ");
        String tipoDoc = scanner.nextLine();
        System.out.print("Numero de documento: ");
        String numDocumento = scanner.nextLine();
        System.out.print("Primer nombre: ");
        String pNombre = scanner.nextLine();
        System.out.print("Segundo nombre: ");
        String sNombre = scanner.nextLine();
        System.out.print("Primer apellido: ");
        String pApellido = scanner.nextLine();
        System.out.print("Segundo apellido: ");
        String sApellido = scanner.nextLine();
        System.out.print("Ciudad de residencia: ");
        String ciudadResidencia = scanner.nextLine();
        System.out.print("Direccion: ");
        String direccion = scanner.nextLine();
        System.out.print("Telefono");
        String telefono = scanner.nextLine();
        System.out.print("Fecha de nacimiento: ");
        String fNacimiento = scanner.nextLine();
        System.out.print("Sexo: ");
        String sexo = scanner.nextLine();
        Profesores profesor = new Profesores(tipoDoc, numDocumento, pNombre, sNombre, pApellido, sApellido,
                ciudadResidencia, direccion, telefono, fNacimiento, sexo);
        serviceProfesores.crear(profesor);
    }

    public static void eliminarProfesores() {
        Profesores profesor = buscarGetProfesor();
        if (profesor != null) {
            serviceProfesores.eliminar(profesor);
            System.out.println("Elmininado el profesor con exito");
        } else {
            System.out.println("Se presentó un problema y no se puedo eliminar el profesor");
        }

    }

    // Cambios

    private static void mostrarListaDepartamentos() {
        System.out.println("Lista de Departamentos:");
        List<Departamentos> departamentos = serviceDepartamentos.listar();
        for (Departamentos departamento : departamentos) {
            System.out.println("ID: " + departamento.getId_departamento() + ", Nombre: " + departamento.getNomDepartamento());
        }
    }
    
    private static Departamentos buscarGetDepartamento() {
        mostrarListaDepartamentos();
        System.out.print("Ingrese el ID del departamento seleccionado: ");
        int idDepartamento = scanner.nextInt();
        scanner.nextLine();
        List<Departamentos> departamentos = serviceDepartamentos.listar();
        for (Departamentos departamento : departamentos) {
            if (departamento.getId_departamento() == idDepartamento) {
                return departamento;
            }
        }
        System.out.println("El ID del departamento ingresado no es válido.");
        return null;
    }
    
    public static void asignarDepartamento() {
        Departamentos departamento = buscarGetDepartamento();
        if (departamento != null) {
            Profesores profesor = buscarGetProfesor();
            if (profesor != null) {
                if (profesor.getId_departamento() != null) {
                    System.out.println("El profesor ya está asignado a un departamento.");
                    return;
                } else {
                    profesor.setId_departamento(departamento);
                    serviceProfesores.asignarDepartamento(profesor, departamento);
                    System.out.println("Departamento asignado al profesor con éxito");
                }
            } else {
                System.out.println("No se encontró el profesor");
            }
        } else {
            System.out.println("No se encontró el departamento");
        }
    }
    
    
    
    

    public static void obtenerProfesoresPorDepartamento() {
        Departamentos departamento = buscarGetDepartamento();
        if (departamento != null) {
            List<Profesores> profesores = serviceProfesores.obtenerProfesoresPorDepartamento(departamento);
            if (!profesores.isEmpty()) {
                System.out.println("Profesores en el departamento " + departamento.getNomDepartamento() + ":");
                for (Profesores profesor : profesores) {
                    profesor.imprimirProfesor();
                }
            } else {
                System.out.println("No hay profesores en este departamento.");
            }
        } else {
            System.out.println("No se encontró el departamento");
        }
    }

    public static int mostrarMenu() {
        System.out.println("\u001B[34m╔══════════════════════════════════╗");
        System.out.println("║\u001B[30m         MENU PROFESORES          \u001B[34m║");
        System.out.println("╠══════════════════════════════════╣");
        System.out.println("\u001B[33m║ 1. Crear profesor                ║");
        System.out.println("║ 2. Buscar profesor               ║");
        System.out.println("║ 3. Listar profesores             ║");
        System.out.println("║ 4. Eliminar profesor             ║");
        System.out.println("║ 5. Asignar departamento          ║");
        System.out.println("║ 6. Obtener profesores por depto  ║");
        System.out.println("║ 7. Salir                         ║");
        System.out.println("╚══════════════════════════════════╝\u001B[0m");
        System.out.print("\u001B[33m --> \u001B[0m");
        return scanner.nextInt();
    }
    

}

package com.proyecto.views;

import java.util.List;

import com.proyecto.exceptiones.asignaturasexceptions.AsignaturaNullExceptions;
import com.proyecto.repositories.models.Asignaturas;
import com.proyecto.repositories.models.Cursos;
import com.proyecto.repositories.models.Periodos;
import com.proyecto.repositories.models.Profesores;
import com.proyecto.repositories.models.Programas;

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
        List<Asignaturas> asignaturas = serviceAsignatura.listar();
        if (asignaturas.isEmpty()) {
            System.out.println("No hay asignaturas registradas.");
        } else {
            for (Asignaturas asignatura : asignaturas) {
                asignatura.imprimirAsignatura();
                System.out.println();
            }
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
        System.out.println("Crear nueva asignatura:");

        List<Profesores> profesores = serviceProfesores.listar();
        System.out.println("Lista de profesores disponibles:");
        for (Profesores profesor : profesores) {
            System.out.println(profesor.getId_profesor() + ". " + profesor.getNombreCompleto());
        }
        System.out.print("Seleccione el ID del profesor: ");
        int idProfesor = scanner.nextInt();
        scanner.nextLine();  
    

        List<Programas> programas = serviceProgramas.listar();
        System.out.println("Lista de programas disponibles:");
        for (Programas programa : programas) {
            System.out.println(programa.getId_programa() + ". " + programa.getNomPrograma());
        }
        System.out.print("Seleccione el ID del programa: ");
        int idPrograma = scanner.nextInt();
        scanner.nextLine(); 
    

        List<Cursos> cursos = serviceCursos.listar();
        System.out.println("Lista de cursos disponibles:");
        for (Cursos curso : cursos) {
            System.out.println(curso.getId_curso() + ". " + curso.getNomCurso());
        }
        System.out.print("Seleccione el ID del curso: ");
        int idCurso = scanner.nextInt();
        scanner.nextLine();  
    

        List<Periodos> periodos = servicePeriodos.listar();
        System.out.println("Lista de periodos disponibles:");
        for (Periodos periodo : periodos) {
            System.out.println(periodo.getId_periodo() + ". " + periodo.getCodPeriodo());
        }
        System.out.print("Seleccione el ID del período: ");
        int idPeriodo = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Ingrese el cupo de la asignatura: ");
        String cupoAsignatura = scanner.nextLine().trim();
        if (cupoAsignatura.isEmpty()) {
            System.out.println("El cupo de la asignatura no puede estar vacío.");
            return;
        }

        System.out.print("Ingrese los créditos de la asignatura: ");
        String creditos = scanner.nextLine().trim();
        if (creditos.isEmpty()) {
            System.out.println("Los créditos de la asignatura no pueden estar vacíos.");
            return;
        }
    

        Profesores profesor = profesores.stream().filter(p -> p.getId_profesor() == idProfesor).findFirst().orElse(null);
        if (profesor == null) {
            System.out.println("Profesor no encontrado.");
            return;
        }

        Programas programa = programas.stream().filter(p -> p.getId_programa() == idPrograma).findFirst().orElse(null);
        if (programa == null) {
            System.out.println("Programa no encontrado.");
            return;
        }

        Cursos curso = cursos.stream().filter(c -> c.getId_curso() == idCurso).findFirst().orElse(null);
        if (curso == null) {
            System.out.println("Curso no encontrado.");
            return;
        }

        Periodos periodo = periodos.stream().filter(p -> p.getId_periodo() == idPeriodo).findFirst().orElse(null);
        if (periodo == null) {
            System.out.println("Periodo no encontrado.");
            return;
        }
    

        String nomAsignatura = generarNomAsignatura(profesor, programa, curso, periodo);
 
        Asignaturas asignatura = new Asignaturas(nomAsignatura, cupoAsignatura, creditos, curso, periodo, programa);
        asignatura.setProfesor(profesor);

        serviceAsignatura.crear(asignatura);
    }
    

    private static String generarNomAsignatura(Profesores profesor, Programas programa, Cursos curso, Periodos periodo) {
        return "Asignatura_" + profesor.getId_profesor() + "_" + programa.getId_programa() + "_" + curso.getId_curso() + "_" + periodo.getId_periodo();
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
        System.out.println("\u001B[34m╔══════════════════════════════════════╗");
        System.out.println("║\u001B[31m          MENU DE ASIGNATURA          \u001B[34m║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║ \u001B[33m1. Crear asignatura                  \u001B[34m║");
        System.out.println("║ \u001B[33m2. Buscar asignatura                 \u001B[34m║");
        System.out.println("║ \u001B[33m3. Listar asignaturas                \u001B[34m║");
        System.out.println("║ \u001B[33m4. Eliminar asignatura               \u001B[34m║");
        System.out.println("║ \u001B[33m5. Salir                             \u001B[34m║");
        System.out.println("╚══════════════════════════════════════╝\u001B[0m");
        System.out.print("\u001B[33m --> \u001B[0m");
        return scanner.nextInt();
    }
    
}

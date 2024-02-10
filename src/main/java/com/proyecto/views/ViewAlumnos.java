package com.proyecto.views;

import com.proyecto.exceptiones.alumnosexceptions.AlumnosNullException;
import com.proyecto.repositories.models.Alumnos;

public class ViewAlumnos extends ViewMain {
    public static void startMenu(){
        int op = 0;

        do {
            op = mostrarMenu();

            switch (op) {
                case 1:
                    crearAlumno();
                    break;
                case 2:
                    buscarAlumno();
                    break;
                case 3:
                    listarAlumnos();
                    break;
                case 4:
                    eliminarAlumnos();
                default:
                    System.out.println("Opcion invalida.");
                    break;
            }
        } while (op >= 1 && op < 5);
    }

    public static void listarAlumnos() {
        System.out.println("Lista de Clientes");
        for (Alumnos alumno : serviceAlumnos.listar()) {
            alumno.imprimirAlumno();
            System.out.println();
        }
    }

    public static Alumnos buscarGetAlumno() {
        System.out.println("Busqueda de cliente "); 
        scanner.nextLine();       
        System.out.print("Documento: ");
        String numDocumento = scanner.nextLine();

        try {
            return serviceAlumnos.buscar(numDocumento);

        } catch (AlumnosNullException e) {

            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void buscarAlumno(){
        System.out.println("Buscar alumno");
        scanner.nextLine();
        System.out.println("Documento: ");
        String numDocumento = scanner.nextLine();

        try{
            Alumnos alumno = serviceAlumnos.buscar(numDocumento);
            System.out.println();
            alumno.imprimirAlumno();
        }catch(AlumnosNullException e){
            System.out.println(e.getMessage());
        }
    }

    public static void crearAlumno() {
        scanner.nextLine();
        System.out.print("Tipo de documento: ");
        String tipoDocumento = scanner.nextLine();
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
        Alumnos alumno = new Alumnos(tipoDocumento, numDocumento, pNombre, sNombre, pApellido, sApellido, ciudadResidencia, direccion, telefono, fNacimiento, sexo);
        serviceAlumnos.crear(alumno);
    }

    public static void eliminarAlumnos() {
        Alumnos alumno = buscarGetAlumno();
        if (alumno != null) {
            serviceAlumnos.eliminar(alumno);
            System.out.println("Elmininado el alumno con exito");
        } else {
            System.out.println("Se presentó un problema y no se puedo eliminar el alumno");
        }

    }

    public static int mostrarMenu(){
        System.out.println("###MENU ALUMNO###");
        System.out.println("1. Crear alumno");
        System.out.println("2. Buscar alumno");
        System.out.println("3. Listar alumnos");
        System.out.println("4. Eliminar alumno");
        System.out.println("5. Salir");
        return scanner.nextInt();
    }
}


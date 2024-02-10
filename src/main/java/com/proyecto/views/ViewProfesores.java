package com.proyecto.views;

import com.proyecto.exceptiones.alumnosexceptions.AlumnosNullException;
import com.proyecto.repositories.models.Profesores;

public class ViewProfesores extends ViewMain{
        public static void startMenu(){
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
                default:
                    System.out.println("Opcion invalida.");
                    break;
            }
        } while (op >= 1 && op < 5);
    }

    public static void listarProfesores() {
        System.out.println("Lista de Clientes");
        for (Profesores profesor : serviceProfesores.listar()) {
            profesor.imprimirProfesor();
            System.out.println();
        }
    }

    public static Profesores buscarGetProfesor() {
        System.out.println("Busqueda de cliente "); 
        scanner.nextLine();       
        System.out.print("Documento: ");
        String numDocumento = scanner.nextLine();

        try {
            return serviceProfesores.buscar(numDocumento);

        } catch (AlumnosNullException e) {

            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void buscarProfesores(){
        System.out.println("Buscar profesor");
        scanner.nextLine();
        System.out.println("Documento: ");
        String numDocumento = scanner.nextLine();

        try{
            Profesores profesor = serviceProfesores.buscar(numDocumento);
            System.out.println();
            profesor.imprimirProfesor();
        }catch(AlumnosNullException e){
            System.out.println(e.getMessage());
        }
    }

    public static void crearProfesores() {
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
        Profesores profesor = new Profesores(tipoDocumento, numDocumento, pNombre, sNombre, pApellido, sApellido, ciudadResidencia, direccion, telefono, fNacimiento, sexo);
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

    public static int mostrarMenu(){
        System.out.println("###MENU PROFESORES###");
        System.out.println("1. Crear profesor");
        System.out.println("2. Buscar profesor");
        System.out.println("3. Listar profesores");
        System.out.println("4. Eliminar profesor");
        System.out.println("5. Salir");
        return scanner.nextInt();
    }
}

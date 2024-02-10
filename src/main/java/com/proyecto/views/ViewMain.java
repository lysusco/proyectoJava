package com.proyecto.views;

import java.util.Scanner;

import com.proyecto.repositories.impl.implalumnos.RepositoryAlumnosMysqlImpl;
import com.proyecto.repositories.impl.implcursos.RepositoryCursosMysqlImpl;
import com.proyecto.repositories.impl.implprofesores.RepositoryProfesoresMysqlImpl;
import com.proyecto.services.ServiceAlumnos;
import com.proyecto.services.ServiceCursos;
import com.proyecto.services.ServiceProfesores;
import com.proyecto.services.impl.ServiceAlumnosImpl;
import com.proyecto.services.impl.ServiceCursosImpl;
import com.proyecto.services.impl.ServiceProfesoresImpl;

public class ViewMain{
    
    public static final ServiceAlumnos serviceAlumnos = new ServiceAlumnosImpl(new RepositoryAlumnosMysqlImpl());
    public static final ServiceProfesores serviceProfesores = new ServiceProfesoresImpl(new RepositoryProfesoresMysqlImpl());
    public static final ServiceCursos serviceCursos = new ServiceCursosImpl(new RepositoryCursosMysqlImpl());
    public static final Scanner scanner = new Scanner(System.in);   

    public static void main(String[] args) {
        int op = 0;

        do {
            op = menuMain();
            switch (op) {
                case 1:
                    ViewAlumnos.startMenu();
                    break;
                case 2:
                    ViewProfesores.startMenu();
                case 3:
                    ViewCursos.startMenu();
                default:
                    break;
            }
            
        } while (op >= 1 && op < 3);
    }

    public static int menuMain(){
        System.out.println("### APLICACION MATRICULAS ###");
        System.out.println("1. Modulo de Alumnos");
        System.out.println("2. Modulo de Profesores");
        System.out.println("3. Modulo de Cursos");
        System.out.println("4. Salir");
        return scanner.nextInt();
    }
}
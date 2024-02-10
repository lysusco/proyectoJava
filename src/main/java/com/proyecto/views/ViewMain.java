package com.proyecto.views;

import java.util.Scanner;

import com.proyecto.repositories.impl.implalumnos.RepositoryAlumnosMysqlImpl;
import com.proyecto.services.ServiceAlumnos;
import com.proyecto.services.impl.ServiceAlumnosImpl;

public class ViewMain{
    
    public static final ServiceAlumnos serviceAlumnos = new ServiceAlumnosImpl(new RepositoryAlumnosMysqlImpl());
    public static final Scanner scanner = new Scanner(System.in);    
    public static void main(String[] args) {
        int op = 0;

        do {
            op = menuMain();
            switch (op) {
                case 1:
                    ViewAlumnos.startMenu();
                    break;
            
                default:
                    break;
            }
            
        } while (op >= 1 && op < 3);
    }

    public static int menuMain(){
        System.out.println("### APLICACION MATRICULAS ###");
        System.out.println("1. Modulo de Alumnos");
        System.out.println("2. Salir");
        return scanner.nextInt();
    }
}
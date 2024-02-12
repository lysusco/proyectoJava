package com.proyecto.views;

import java.util.Scanner;

import com.proyecto.repositories.impl.implalumnos.RepositoryAlumnosMysqlImpl;
import com.proyecto.repositories.impl.implasignaturas.RepositoryAsignaturasMysqlImpl;
import com.proyecto.repositories.impl.implcursos.RepositoryCursosMysqlImpl;
import com.proyecto.repositories.impl.impldepartamentos.RepositoryDepartamentosMysqlImpl;
import com.proyecto.repositories.impl.implhorarios.RepositoryHorariosMysqlImpl;
import com.proyecto.repositories.impl.implperiodos.RepositoryPeriodosMysqlImpl;
import com.proyecto.repositories.impl.implprofesores.RepositoryProfesoresMysqlImpl;
import com.proyecto.repositories.impl.implprogramas.RepositoryProgramasMysqlImpl;
import com.proyecto.repositories.impl.implsalones.RepositorySalonesMysqlImpl;
import com.proyecto.repositories.impl.impltarifas.RepositoryTarifasMysqlImpl;
import com.proyecto.services.ServiceAlumnos;
import com.proyecto.services.ServiceAsignatura;
import com.proyecto.services.ServiceCursos;
import com.proyecto.services.ServiceDepartamentos;
import com.proyecto.services.ServiceHorarios;
import com.proyecto.services.ServicePeriodos;
import com.proyecto.services.ServiceProfesores;
import com.proyecto.services.ServiceProgramas;
import com.proyecto.services.ServiceSalones;
import com.proyecto.services.ServiceTarifas;
import com.proyecto.services.impl.ServiceAlumnosImpl;
import com.proyecto.services.impl.ServiceAsignaturasImpl;
import com.proyecto.services.impl.ServiceCursosImpl;
import com.proyecto.services.impl.ServiceDepartamentosImpl;
import com.proyecto.services.impl.ServiceHorariosImpl;
import com.proyecto.services.impl.ServicePeriodosImpl;
import com.proyecto.services.impl.ServiceProfesoresImpl;
import com.proyecto.services.impl.ServiceProgramasImpl;
import com.proyecto.services.impl.ServiceSalonesImpl;
import com.proyecto.services.impl.ServiceTarifasImpl;

public class ViewMain{
    
    public static final ServiceAlumnos serviceAlumnos = new ServiceAlumnosImpl(new RepositoryAlumnosMysqlImpl());
    public static final ServiceProfesores serviceProfesores = new ServiceProfesoresImpl(new RepositoryProfesoresMysqlImpl());
    public static final ServiceCursos serviceCursos = new ServiceCursosImpl(new RepositoryCursosMysqlImpl());
    public static final ServiceDepartamentos serviceDepartamentos = new ServiceDepartamentosImpl(new RepositoryDepartamentosMysqlImpl());
    public static final ServiceProgramas serviceProgramas = new ServiceProgramasImpl(new RepositoryProgramasMysqlImpl());
    public static final ServiceHorarios serviceHorarios = new ServiceHorariosImpl(new RepositoryHorariosMysqlImpl());
    public static final ServiceAsignatura serviceAsignatura = new ServiceAsignaturasImpl(new RepositoryAsignaturasMysqlImpl());
    public static final ServicePeriodos servicePeriodos = new ServicePeriodosImpl(new RepositoryPeriodosMysqlImpl());
    public static final ServiceSalones serviceSalones = new ServiceSalonesImpl(new RepositorySalonesMysqlImpl());
    public static final ServiceTarifas serviceTarifas = new ServiceTarifasImpl(new RepositoryTarifasMysqlImpl());
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
                    break;
                case 3:
                    ViewCursos.startMenu();
                    break;
                case 4:
                    ViewDepartamentos.startMenu();
                    break;
                case 5:
                    ViewHorarios.startMenu();
                    break;
                case 6:
                    ViewAsignaturas.startMenu();
                    break;      
                default:
                    break;
            }
            
        } while (op >= 1 && op < 4);
    }

    public static int menuMain(){
        System.out.println("### APLICACION MATRICULAS ###");
        System.out.println("1. Modulo de Alumnos");
        System.out.println("2. Modulo de Profesores");
        System.out.println("3. Modulo de Cursos");
        System.out.println("4. Modulo de Departamentos");
        System.out.println("5. Salir");
        return scanner.nextInt();
    }
}
package com.proyecto.repositories.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Asignaturas {
    private int id_asignatura;
    private String codigo;
    private String nomAsignatura;
    private String cupoAsignatura;
    private String creditos;
    private Cursos cursos;
    private Periodos periodos;
    private Programas programas;
    private Profesores profesor;

    public Asignaturas(String nomAsignatura, String cupoAsignatura, String creditos, Cursos cursos, Periodos periodos, Programas programas, Profesores profesor) {
        this.nomAsignatura = nomAsignatura;
        this.cupoAsignatura = cupoAsignatura;
        this.creditos = creditos;
        this.cursos = cursos;
        this.periodos = periodos;
        this.programas = programas;
        this.profesor = profesor;
        this.codigo = generarCodigo();
    }    

    

    public Asignaturas(String cupoAsignatura, String creditos, Cursos cursos, Periodos periodos, Programas programas) {
        this.cupoAsignatura = cupoAsignatura;
        this.creditos = creditos;
        this.cursos = cursos;
        this.periodos = periodos;
        this.programas = programas;
    }



    public Asignaturas(String nomAsignatura, String cupoAsignatura, String creditos, Cursos cursos, Periodos periodos,
            Programas programas) {
        this.nomAsignatura = nomAsignatura;
        this.cupoAsignatura = cupoAsignatura;
        this.creditos = creditos;
        this.cursos = cursos;
        this.periodos = periodos;
        this.programas = programas;
    }

    public Asignaturas(String nomAsignatura) {
        this.nomAsignatura = nomAsignatura;
    }


    public String generarCodigo() {
        String codigoPrograma = "";
        if (this.programas != null) {
            codigoPrograma = this.programas.generandoCodigo();
        }
        String codigoCurso = "";
        if (this.cursos != null) {
            codigoCurso = this.cursos.generarCodigo();
        }
        String codigoPeriodo = "";
        if (this.periodos != null) {
            codigoPeriodo = this.periodos.getCodPeriodo();
        }
        String codigoAsignatura = this.nomAsignatura.toUpperCase().replaceAll("\\s", "");
        return codigoPrograma + "-" + codigoCurso + "-" + codigoPeriodo + "-" + codigoAsignatura;
    }
    
    
    
    
    
    public void imprimirAsignatura() {
        System.out.println("\u001B[34m══════════════════════════════════════════");
        System.out.println("\u001B[30m            Datos de la Asignatura         \u001B[34m");
        System.out.println("══════════════════════════════════════════");
        System.out.println(" Código de asignatura: " + this.generarCodigo());
        System.out.println(" Cupo asignatura: " + this.getCupoAsignatura());
        System.out.println(" Créditos asignatura: " + this.getCreditos());
        if (this.getProfesor() != null) {
            System.out.println(" Profesor: " + this.getProfesor().getNombreCompleto());
        } else {
            System.out.println(" Profesor: No asignado");
        }
        System.out.println("══════════════════════════════════════════\u001B[0m");
    }
    
    
    
}

package com.proyecto.repositories.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Asignaturas {
    private int id;
    private String nomAsignatura;
    private String cupoAsignatura;
    private String creditos;

    public Asignaturas(String nomAsignatura, String cupoAsignatura, String creditos) {
        this.nomAsignatura = nomAsignatura;
        this.cupoAsignatura = cupoAsignatura;
        this.creditos = creditos;
    }

    public void imprimirAsignatura(){
        System.out.println("Nombre de asignatura: "+this.getNomAsignatura());
        System.out.println("Cupo asignatura: "+this.getCupoAsignatura());
        System.out.println("Creditos asinatura: "+this.getCreditos());
    }
}

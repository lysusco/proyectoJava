package com.proyecto.repositories.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Asignaturas {
    private String nomAsignatura;
    private int cupoAsignatura;
    private int creditos;

    public Asignaturas(String nomAsignatura, int cupoAsignatura, int creditos) {
        this.nomAsignatura = nomAsignatura;
        this.cupoAsignatura = cupoAsignatura;
        this.creditos = creditos;
    }
}

package com.proyecto.repositories.models;

import java.time.Year;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor

public class Periodos {
    private String codPeriodo;
    private Year año;
    private int semestre;
    
    public Periodos(String codPeriodo, Year año, int semestre) {
        this.codPeriodo = codPeriodo;
        this.año = año;
        this.semestre = semestre;
    }
    
}

package com.proyecto.repositories.models;


import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor

public class Periodos {
    private int id_periodo;
    private String codPeriodo;
    private String año;
    private String semestre;
    
    public Periodos(String codPeriodo, String año, String semestre) {
        this.codPeriodo = codPeriodo;
        this.año = año;
        this.semestre = semestre;
    }

    public Periodos(String año, String semestre) {
        this.año = año;
        this.semestre = semestre;
    }

    public void imprimirPeriodo(){
        System.out.println("Codigo del periodo: "+this.getCodPeriodo()+" año: "+this.getAño()+" semestre: "+this.getSemestre());
    }

    public String getNombre() {
        return this.codPeriodo + "-" + this.año + "-" + this.semestre;
    }

    public String getNombreTarifa(){
        return this.codPeriodo;
    }
    
}

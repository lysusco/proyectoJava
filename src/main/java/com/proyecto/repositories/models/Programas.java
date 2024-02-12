package com.proyecto.repositories.models;

import lombok.Data;
import lombok.NoArgsConstructor;

enum Nivel {
    Pregrado,
    Postgrado
}

@Data
@NoArgsConstructor

public class Programas {
    private int id;
    private String nomPrograma;
    private String nivel;

    public Programas(String nomPrograma, String nivel) {
        this.nomPrograma = nomPrograma;
        this.nivel = nivel;
    }

    public void imprimirPrograma(){
        System.out.println("Nombre del programa: "+this.getNomPrograma());
        System.out.println("Nivel del programa: "+this.getNivel());
    }

}

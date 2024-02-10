package com.proyecto.repositories.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class Cursos {
    private int id;
    private String nomCurso;
    private String guiaCatedra;
    
    public Cursos(String nomCurso, String guiaCatedra) {
        this.nomCurso = nomCurso;
        this.guiaCatedra = guiaCatedra;
    }

    public void imprimirCurso(){
        System.out.println("Nombre del curso: "+this.getNomCurso());
    }

}

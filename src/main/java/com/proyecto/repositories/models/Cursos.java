package com.proyecto.repositories.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class Cursos {
    private String nomCurso;
    private String guiaCatedra;

    public Cursos(String nomCurso, String guiaCatedra) {
        this.nomCurso = nomCurso;
        this.guiaCatedra = guiaCatedra;
    }

}

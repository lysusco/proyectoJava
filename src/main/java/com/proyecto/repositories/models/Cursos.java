package com.proyecto.repositories.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class Cursos {
    private int id_curso;
    private String nomCurso;
    private String guiaCatedra;
    private String codigo;


    public Cursos(String nomCurso, String guiaCatedra) {
        this.nomCurso = nomCurso;
        this.guiaCatedra = guiaCatedra;
        this.codigo = generarCodigo();
    }

    public void imprimirCurso() {
        System.out.println("Nombre del curso: " + this.getNomCurso());
    }

    public String getCodigo() {
        return this.codigo;
    }

    public String generarCodigo() {
        return "CUR-" + this.nomCurso.toUpperCase().replaceAll("\\s", "");
    }
}

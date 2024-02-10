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
    private String nomPrograma;
    private Nivel nivel;

    public Programas(String nomPrograma, Nivel nivel) {
        this.nomPrograma = nomPrograma;
        this.nivel = nivel;
    }

}

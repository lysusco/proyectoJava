package com.proyecto.repositories.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class Departamentos {
    private int id;
    private String nomDepartamento;

    public Departamentos(String nomDepartamento) {
        this.nomDepartamento = nomDepartamento;
    }

    public void imprimirDepartamento(){
        System.out.println("Nombre del departamento: "+this.getNomDepartamento());
    }
}

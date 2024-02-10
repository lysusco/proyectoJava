package com.proyecto.repositories.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class Departamentos {
    private String nomDepartamento;

    public Departamentos(String nomDepartamento) {
        this.nomDepartamento = nomDepartamento;
    }
}

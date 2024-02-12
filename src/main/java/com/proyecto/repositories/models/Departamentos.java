package com.proyecto.repositories.models;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class Departamentos {
    private int id_departamento;
    private String nomDepartamento;
    private List<Profesores> profesoresAsociados;

    public Departamentos(String nomDepartamento) {
        this.nomDepartamento = nomDepartamento;
        this.profesoresAsociados = new ArrayList<>();
    }

        public void agregarProfesor(Profesores profesor) {
        this.profesoresAsociados.add(profesor);
    }

    public void eliminarProfesor(Profesores profesor) {
        this.profesoresAsociados.remove(profesor);
    }

    public List<Profesores> obtenerProfesoresAsociados() {
        return this.profesoresAsociados;
    }

    public void imprimirDepartamento() {
        System.out.println("\u001B[34m══════════════════════════════════════");
        System.out.println("\u001B[30m          Datos del Departamento        \u001B[34m");
        System.out.println("══════════════════════════════════════");
        System.out.println(" Nombre: " + this.getNomDepartamento());
        System.out.println("══════════════════════════════════════\u001B[0m");
    }
    
}

package com.proyecto.repositories;

import java.util.List;

import com.proyecto.repositories.models.Alumnos;

public interface RepositoryAlumnos {
    List<Alumnos> listar();

    Alumnos buscar(String numDocumento);

    void crear(Alumnos alumno);

    void editar(Alumnos alumno);
    
    void eliminar(Alumnos alumno);

}

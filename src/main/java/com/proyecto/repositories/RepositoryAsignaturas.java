package com.proyecto.repositories;

import java.util.List;

import com.proyecto.repositories.models.Asignaturas;

public interface RepositoryAsignaturas {

    List<Asignaturas> listar();

    Asignaturas buscar();
    
    void crear(Asignaturas asignaturas);
    
    void editar(Asignaturas asignaturas);
    
    void eliminar(Asignaturas asignaturas);
    
}

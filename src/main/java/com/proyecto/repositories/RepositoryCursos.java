package com.proyecto.repositories;

import java.util.List;

import com.proyecto.repositories.models.Cursos;


public interface RepositoryCursos {
    
    List<Cursos> listar();

    Cursos buscar();
    
    void crear(Cursos cursos);
    
    void editar(Cursos cursos);
    
    void eliminar(Cursos cursos);
}

package com.proyecto.repositories;

import java.util.List;

import com.proyecto.repositories.models.Profesores;

public interface RepositoryProfesores {

    List<Profesores> listar();

    Profesores buscar(int numDocumento);
    
    void crear(Profesores profesores);
    
    void editar(Profesores profesores);
    
    void eliminar(Profesores profesores);
}

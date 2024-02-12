package com.proyecto.repositories;

import java.util.List;

import com.proyecto.repositories.models.Departamentos;
import com.proyecto.repositories.models.Profesores;

public interface RepositoryProfesores {

    List<Profesores> listar();

    Profesores buscar(String numDocumento);
    
    void crear(Profesores profesores);
    
    void editar(Profesores profesores);
    
    void eliminar(Profesores profesores);

    //Cambios

    void asignarDepartamento(Profesores profesor, Departamentos departamento);

    List<Profesores> obtenerProfesoresPorDepartamento(Departamentos departamento);

}

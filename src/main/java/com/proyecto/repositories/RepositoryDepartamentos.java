package com.proyecto.repositories;

import java.util.List;

import com.proyecto.repositories.models.Departamentos;
import com.proyecto.repositories.models.Profesores;



public interface RepositoryDepartamentos{

    List<Departamentos> listar();

    Departamentos buscar(String nomDepartamento);
    
    void crear(Departamentos departamentos);
    
    void editar(Departamentos departamentos);
    
    void eliminar(Departamentos departamentos);

    //Cambios

    Departamentos obtenerDepartamentoPorProfesor(Profesores profesor);

}

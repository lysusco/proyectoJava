package com.proyecto.repositories;

import java.util.List;

import com.proyecto.repositories.models.Departamentos;



public interface RepositoryDepartamentos{

    List<Departamentos> listar();

    Departamentos buscar();
    
    void crear(Departamentos departamentos);
    
    void editar(Departamentos departamentos);
    
    void eliminar(Departamentos departamentos);
    
}

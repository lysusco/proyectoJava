package com.proyecto.services;

import java.util.List;

import com.proyecto.exceptiones.departamentosexceptions.DepartamentosNullException;
import com.proyecto.repositories.models.Departamentos;
import com.proyecto.repositories.models.Profesores;

public interface ServiceDepartamentos {
    
    List<Departamentos> listar();

    Departamentos buscar(String nomDepartamento) throws DepartamentosNullException;
    
    void crear(Departamentos departamentos);
    
    void editar(Departamentos departamentos);
    
    void eliminar(Departamentos departamentos);

    Departamentos obtenerDepartamentoPorProfesor(Profesores profesor);
}

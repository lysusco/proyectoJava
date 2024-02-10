package com.proyecto.services;

import java.util.List;

import com.proyecto.exceptiones.alumnosexceptions.AlumnosNullException;
import com.proyecto.repositories.models.Alumnos;

public interface ServiceAlumnos {

    List<Alumnos> listar();

    Alumnos buscar(String numDocumento)throws AlumnosNullException;

    void crear(Alumnos alumnos);

    void editar(Alumnos alumnos);
    
    void eliminar(Alumnos cliente);
}

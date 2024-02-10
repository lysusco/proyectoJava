package com.proyecto.services;

import java.util.List;

import com.proyecto.exceptiones.alumnosexceptions.AlumnosNullException;
import com.proyecto.repositories.models.Profesores;

public interface ServiceProfesores {

    List<Profesores> listar();

    Profesores buscar(String numDocumento)throws AlumnosNullException;

    void crear(Profesores profesores);

    void editar(Profesores profesores);
    
    void eliminar(Profesores profesores);
}
    

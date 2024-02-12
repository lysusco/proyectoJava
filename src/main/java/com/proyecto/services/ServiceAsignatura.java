package com.proyecto.services;

import java.util.List;

import com.proyecto.exceptiones.asignaturasexceptions.AsignaturaNullExceptions;
import com.proyecto.repositories.models.Asignaturas;

public interface ServiceAsignatura {

    
    List<Asignaturas> listar();

    Asignaturas buscar(String nomAsignatura) throws AsignaturaNullExceptions;
    
    void crear(Asignaturas asignaturas);
    
    void editar(Asignaturas asignaturas);
    
    void eliminar(Asignaturas asignaturas);
}

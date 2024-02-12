package com.proyecto.services;

import java.util.List;

import com.proyecto.exceptiones.programasexceptions.ProgramasNullException;
import com.proyecto.repositories.models.Programas;

public interface ServiceProgramas {

    List<Programas> listar();

    Programas buscar(String nomPrograma) throws ProgramasNullException;
    
    void crear(Programas programas);
    
    void editar(Programas programas);
    
    void eliminar(Programas programas);
    
}

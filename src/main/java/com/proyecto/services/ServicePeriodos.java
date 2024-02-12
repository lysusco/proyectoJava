package com.proyecto.services;

import java.util.List;

import com.proyecto.exceptiones.periodosexceptions.PeriodosNullException;
import com.proyecto.repositories.models.Periodos;

public interface ServicePeriodos {

    List<Periodos> listar();

    Periodos buscar(String codPeriodo) throws PeriodosNullException;
    
    void crear(Periodos periodos);
    
    void editar(Periodos periodos);
    
    void eliminar(Periodos periodos);
}

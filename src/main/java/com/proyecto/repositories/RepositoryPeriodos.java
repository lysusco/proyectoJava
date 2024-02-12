package com.proyecto.repositories;

import java.util.List;

import com.proyecto.repositories.models.Periodos;

public interface RepositoryPeriodos {

    List<Periodos> listar();

    Periodos buscar(String codPeriodo);
    
    void crear(Periodos periodos);
    
    void editar(Periodos periodos);
    
    void eliminar(Periodos periodos);
}

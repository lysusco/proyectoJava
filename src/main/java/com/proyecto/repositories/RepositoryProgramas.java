package com.proyecto.repositories;

import java.util.List;

import com.proyecto.repositories.models.Programas;

public interface RepositoryProgramas {

    List<Programas> listar();

    Programas buscar();
    
    void crear(Programas programas);
    
    void editar(Programas programas);
    
    void eliminar(Programas programas);
}

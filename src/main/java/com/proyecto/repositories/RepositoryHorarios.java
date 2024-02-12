package com.proyecto.repositories;

import java.util.List;

import com.proyecto.repositories.models.Horarios;

public interface RepositoryHorarios {

    List<Horarios> listar();

    Horarios buscar(String dia);
    
    void crear(Horarios horarios);
    
    void editar(Horarios horarios);
    
    void eliminar(Horarios horarios);
    
}

package com.proyecto.repositories;

import java.util.List;

import com.proyecto.repositories.models.Salones;

public interface RepositorySalones {

    List<Salones> listar();

    Salones buscar(String referenciaSalon);
    
    void crear(Salones salon);
    
    void editar(Salones salon);
    
    void eliminar(Salones salon);
    
}

package com.proyecto.repositories;

import java.util.List;

import com.proyecto.repositories.models.Salones;

public interface RepositorySalones {

    List<Salones> listar();

    Salones buscar();
    
    void crear(Salones programas);
    
    void editar(Salones programas);
    
    void eliminar(Salones programas);
    
}

package com.proyecto.repositories;

import java.util.List;

import com.proyecto.repositories.models.Tarifas;

public interface RepositoryTarifas {

    List<Tarifas> listar();
    
    void crear(Tarifas tarifas);
    
    void editar(Tarifas tarifas);
    
    void eliminar(Tarifas tarifas);
}

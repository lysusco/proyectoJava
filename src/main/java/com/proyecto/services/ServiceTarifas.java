package com.proyecto.services;

import java.util.List;

import com.proyecto.repositories.models.Tarifas;

public interface ServiceTarifas {

    List<Tarifas> listar();
    
    void crear(Tarifas tarifas);
    
    void editar(Tarifas tarifas);
    
    void eliminar(Tarifas tarifas);
}

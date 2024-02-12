package com.proyecto.services;

import java.util.List;

import com.proyecto.repositories.models.Periodos;
import com.proyecto.repositories.models.Programas;
import com.proyecto.repositories.models.Tarifas;

public interface ServiceTarifas {

    List<Tarifas> listarConPeriodos();
    
    void crear(Tarifas tarifas);
    
    void editar(Tarifas tarifas);
    
    void eliminar(Tarifas tarifas);

    List<Tarifas> buscarPorPeriodo(Periodos periodo);

    List<Tarifas> buscarPorPrograma(Programas programa);
}

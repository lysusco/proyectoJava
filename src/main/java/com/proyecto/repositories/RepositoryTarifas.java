package com.proyecto.repositories;

import java.util.List;

import com.proyecto.repositories.models.Tarifas;
import com.proyecto.repositories.models.Periodos;
import com.proyecto.repositories.models.Programas;

public interface RepositoryTarifas {

    List<Tarifas> listarConPeriodos();
    
    List<Tarifas> listarPorPrograma();

    List<Tarifas> buscarPorPeriodo(Periodos periodo);

    List<Tarifas> buscarPorPrograma(Programas programa);
    
    void crear(Tarifas tarifas);
    
    void editar(Tarifas tarifas);
    
    void eliminar(Tarifas tarifas);
}

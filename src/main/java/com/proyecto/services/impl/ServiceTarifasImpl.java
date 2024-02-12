package com.proyecto.services.impl;

import java.util.List;

import com.proyecto.repositories.RepositoryTarifas;
import com.proyecto.repositories.models.Tarifas;
import com.proyecto.services.ServiceTarifas;

public class ServiceTarifasImpl implements ServiceTarifas{

    private final RepositoryTarifas crudRepositoryTarifas;

    public ServiceTarifasImpl(RepositoryTarifas crudRepositoryTarifas){
        this.crudRepositoryTarifas = crudRepositoryTarifas;
    }

    @Override
    public List<Tarifas> listar() {
        return this.crudRepositoryTarifas.listar();
    }

    @Override
    public void crear(Tarifas tarifas) {
        this.crudRepositoryTarifas.crear(tarifas);
    }

    @Override
    public void editar(Tarifas tarifas) {
        this.crudRepositoryTarifas.editar(tarifas);
    }

    @Override
    public void eliminar(Tarifas tarifas) {
        this.crudRepositoryTarifas.eliminar(tarifas);
    }
    
}

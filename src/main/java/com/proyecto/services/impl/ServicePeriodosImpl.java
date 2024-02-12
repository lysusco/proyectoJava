package com.proyecto.services.impl;

import java.util.List;

import com.proyecto.exceptiones.periodosexceptions.PeriodosNullException;
import com.proyecto.repositories.RepositoryPeriodos;
import com.proyecto.repositories.models.Periodos;
import com.proyecto.services.ServicePeriodos;

public class ServicePeriodosImpl implements ServicePeriodos{

    private final RepositoryPeriodos crudRespositoryPeriodos;

    public ServicePeriodosImpl(RepositoryPeriodos crudRespositoryPeriodos){
        this.crudRespositoryPeriodos = crudRespositoryPeriodos;
    }

    @Override
    public List<Periodos> listar() {
        return this.crudRespositoryPeriodos.listar();
    }

    @Override
    public Periodos buscar(String codPeriodo)throws PeriodosNullException {
        Periodos periodo = this.crudRespositoryPeriodos.buscar(codPeriodo);
        if(periodo != null){
            return periodo;
        }else{
            throw new PeriodosNullException(codPeriodo);
        }
    }

    @Override
    public void crear(Periodos periodos) {
        this.crudRespositoryPeriodos.crear(periodos);
    }

    @Override
    public void editar(Periodos periodos) {
        this.crudRespositoryPeriodos.editar(periodos);
    }

    @Override
    public void eliminar(Periodos periodos) {
        this.crudRespositoryPeriodos.eliminar(periodos);
    }
    
}

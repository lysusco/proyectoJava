package com.proyecto.services.impl;

import java.util.List;


import com.proyecto.exceptiones.asignaturasexceptions.AsignaturaNullExceptions;
import com.proyecto.repositories.RepositoryAsignaturas;
import com.proyecto.repositories.models.Asignaturas;
import com.proyecto.services.ServiceAsignatura;

public class ServiceAsignaturasImpl implements ServiceAsignatura{

    private final RepositoryAsignaturas crudRepositoryAsignaturas;

    public ServiceAsignaturasImpl(RepositoryAsignaturas crudRepositoryAsignaturas){
        this.crudRepositoryAsignaturas = crudRepositoryAsignaturas;
    }

    @Override
    public List<Asignaturas> listar() {
        return this.crudRepositoryAsignaturas.listar();
    }

    @Override
    public Asignaturas buscar(String nomAsignatura)throws AsignaturaNullExceptions{
        Asignaturas  asignatura = this.crudRepositoryAsignaturas.buscar(nomAsignatura);
        if(asignatura != null){
            return asignatura;
        }else{
            throw new AsignaturaNullExceptions("NO");
        }
    }

    @Override
    public void crear(Asignaturas asignaturas) {
        this.crudRepositoryAsignaturas.crear(asignaturas);
    }

    @Override
    public void editar(Asignaturas asignaturas) {
        this.crudRepositoryAsignaturas.editar(asignaturas);
    }

    @Override
    public void eliminar(Asignaturas asignaturas) {
        this.crudRepositoryAsignaturas.eliminar(asignaturas);
    }
    
}

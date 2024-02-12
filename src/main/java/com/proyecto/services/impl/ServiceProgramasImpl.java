package com.proyecto.services.impl;

import java.util.List;

import com.proyecto.exceptiones.programasexceptions.ProgramasNullException;
import com.proyecto.repositories.RepositoryProgramas;
import com.proyecto.repositories.models.Programas;
import com.proyecto.services.ServiceProgramas;

public class ServiceProgramasImpl implements ServiceProgramas{

    private final RepositoryProgramas crudRepositoryProgramas;

    public ServiceProgramasImpl(RepositoryProgramas crudRepositoryProgramas){
        this.crudRepositoryProgramas = crudRepositoryProgramas;
    }

    @Override
    public List<Programas> listar() {
        return this.crudRepositoryProgramas.listar();
    }

    @Override
    public Programas buscar(String nomPrograma) throws ProgramasNullException {
        Programas programa = this.crudRepositoryProgramas.buscar(nomPrograma);
        if(programa != null){
            return programa;
        }else{
            throw new ProgramasNullException("Holaaa eso no se hace");
        }
    }

    @Override
    public void crear(Programas programas) {
        this.crudRepositoryProgramas.crear(programas);
    }

    @Override
    public void editar(Programas programas) {
        this.crudRepositoryProgramas.editar(programas);;
    }

    @Override
    public void eliminar(Programas programas) {
        this.crudRepositoryProgramas.eliminar(programas);;
    }
    
}

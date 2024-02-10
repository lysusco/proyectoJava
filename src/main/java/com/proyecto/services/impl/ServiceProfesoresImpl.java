package com.proyecto.services.impl;

import java.util.List;

import com.proyecto.exceptiones.alumnosexceptions.AlumnosNullException;
import com.proyecto.repositories.RepositoryProfesores;
import com.proyecto.repositories.models.Profesores;
import com.proyecto.services.ServiceProfesores;

public class ServiceProfesoresImpl implements ServiceProfesores {

    private final RepositoryProfesores crudRepositoryProfesores;

    public ServiceProfesoresImpl(RepositoryProfesores crudRepositoryProfesores){
        this.crudRepositoryProfesores = crudRepositoryProfesores;
    }

    @Override
    public List<Profesores> listar() {
        return this.crudRepositoryProfesores.listar();
    }

    @Override
    public Profesores buscar(String numDocumento) throws AlumnosNullException {
        Profesores profesores = this.crudRepositoryProfesores.buscar(numDocumento);
        if(profesores != null){
            return profesores;
        }else{
            throw new AlumnosNullException("Holaaa eso no se hace");
        }
    }

    @Override
    public void crear(Profesores profesores) {
        this.crudRepositoryProfesores.crear(profesores);
    }

    @Override
    public void editar(Profesores profesores) {
        this.crudRepositoryProfesores.editar(profesores);
    }

    @Override
    public void eliminar(Profesores profesores) {
        this.crudRepositoryProfesores.eliminar(profesores);
    }
    
}

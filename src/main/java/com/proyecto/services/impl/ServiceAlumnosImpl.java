package com.proyecto.services.impl;

import java.util.List;

import com.proyecto.exceptiones.alumnosexceptions.AlumnosNullException;
import com.proyecto.repositories.RepositoryAlumnos;
import com.proyecto.repositories.models.Alumnos;
import com.proyecto.services.ServiceAlumnos;

public class ServiceAlumnosImpl implements ServiceAlumnos{

    private final RepositoryAlumnos crudRepositoryAlumnos;

    public ServiceAlumnosImpl(RepositoryAlumnos crudRepositoryAlumnos){
        this.crudRepositoryAlumnos = crudRepositoryAlumnos;
    }

    @Override
    public List<Alumnos> listar() {
        return this.crudRepositoryAlumnos.listar();
    }

    @Override
    public Alumnos buscar(String numDocumento)throws AlumnosNullException{
        Alumnos  alumno = this.crudRepositoryAlumnos.buscar(numDocumento);
        if(alumno != null){
            return alumno;
        }else{
            throw new AlumnosNullException("Holaaa eso no se hace");
        }
    }

    @Override
    public void crear(Alumnos alumnos) {
        this.crudRepositoryAlumnos.crear(alumnos);
    }

    @Override
    public void editar(Alumnos alumnos) {
        this.crudRepositoryAlumnos.editar(alumnos);
    }

    @Override
    public void eliminar(Alumnos cliente) {
        this.crudRepositoryAlumnos.eliminar(cliente);
    }
    
}

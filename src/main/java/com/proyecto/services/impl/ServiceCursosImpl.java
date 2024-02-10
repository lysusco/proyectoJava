package com.proyecto.services.impl;

import java.util.List;

import com.proyecto.exceptiones.cursosexceptions.CursosNullException;
import com.proyecto.repositories.RepositoryCursos;
import com.proyecto.repositories.models.Cursos;
import com.proyecto.services.ServiceCursos;

public class ServiceCursosImpl implements ServiceCursos{

    private final RepositoryCursos crudRepositoryCursos;

    public ServiceCursosImpl(RepositoryCursos crudRepositoryCursos){
        this.crudRepositoryCursos = crudRepositoryCursos;
    }

    @Override
    public List<Cursos> listar() {
        return this.crudRepositoryCursos.listar();
    }


    @Override
    public void crear(Cursos cursos) {
        this.crudRepositoryCursos.crear(cursos);
    }

    @Override
    public void editar(Cursos cursos) {
        this.crudRepositoryCursos.editar(cursos);
    }

    @Override
    public void eliminar(Cursos cursos) {
        this.crudRepositoryCursos.eliminar(cursos);
    }

    @Override
    public Cursos buscar(String nomCurso) throws CursosNullException {
               Cursos  curso = this.crudRepositoryCursos.buscar(nomCurso);
        if(curso != null){
            return curso;
        }else{
            throw new CursosNullException("Holaaa eso no se hace");
        }
    }
    
}

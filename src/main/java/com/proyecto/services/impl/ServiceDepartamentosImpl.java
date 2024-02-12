package com.proyecto.services.impl;

import java.util.List;


import com.proyecto.exceptiones.departamentosexceptions.DepartamentosNullException;
import com.proyecto.repositories.RepositoryDepartamentos;
import com.proyecto.repositories.models.Departamentos;
import com.proyecto.repositories.models.Profesores;
import com.proyecto.services.ServiceDepartamentos;

public class ServiceDepartamentosImpl implements ServiceDepartamentos {

    private final RepositoryDepartamentos crudRespositoryDepartamentos;

    public ServiceDepartamentosImpl(RepositoryDepartamentos crudRespositoryDepartamentos){
        this.crudRespositoryDepartamentos = crudRespositoryDepartamentos;
    }

    @Override
    public List<Departamentos> listar() {
        return this.crudRespositoryDepartamentos.listar();
    }

    @Override
    public Departamentos buscar(String nomDepartamento)throws DepartamentosNullException{
        Departamentos  departamento = this.crudRespositoryDepartamentos.buscar(nomDepartamento);
        if(departamento != null){
            return departamento;
        }else{
            throw new DepartamentosNullException("Holaaaaaaaaaaa");
        }
    }

    @Override
    public void crear(Departamentos departamentos) {
        this.crudRespositoryDepartamentos.crear(departamentos);
    }

    @Override
    public void editar(Departamentos departamentos) {
        this.crudRespositoryDepartamentos.editar(departamentos);
    }

    @Override
    public void eliminar(Departamentos departamentos) {
        this.crudRespositoryDepartamentos.eliminar(departamentos);
    }

    @Override
    public Departamentos obtenerDepartamentoPorProfesor(Profesores profesor) {
        return this.crudRespositoryDepartamentos.obtenerDepartamentoPorProfesor(profesor);
    }
    
}

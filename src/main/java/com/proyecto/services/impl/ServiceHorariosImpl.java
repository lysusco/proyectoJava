package com.proyecto.services.impl;

import java.util.List;

import com.proyecto.exceptiones.horariosexceptions.HorariosNullException;
import com.proyecto.repositories.RepositoryHorarios;
import com.proyecto.repositories.models.Horarios;
import com.proyecto.services.ServiceHorarios;

public class ServiceHorariosImpl implements ServiceHorarios{

    private final RepositoryHorarios crudRepositoryHorarios;

    public ServiceHorariosImpl(RepositoryHorarios crudRepositoryHorarios){
        this.crudRepositoryHorarios = crudRepositoryHorarios;
    }

    @Override
    public List<Horarios> listar() {
        return this.crudRepositoryHorarios.listar();
    }

    @Override
    public Horarios buscar(String dia)throws HorariosNullException {
        Horarios horario = this.crudRepositoryHorarios.buscar(dia);
        if(horario != null){
            return horario;
        }else{
            throw new HorariosNullException("Holaaa eso no se hace");
        }
    }

    @Override
    public void crear(Horarios horarios){
        this.crudRepositoryHorarios.crear(horarios);
    }

    @Override
    public void editar(Horarios horarios) {
        this.crudRepositoryHorarios.editar(horarios);
    }

    @Override
    public void eliminar(Horarios horarios) {
        this.crudRepositoryHorarios.eliminar(horarios);
    }
    
}

package com.proyecto.services.impl;

import java.util.List;

import com.proyecto.exceptiones.salonesexceptions.SalonesNullException;
import com.proyecto.repositories.RepositorySalones;
import com.proyecto.repositories.models.Salones;
import com.proyecto.services.ServiceSalones;

public class ServiceSalonesImpl implements ServiceSalones{

    private final RepositorySalones crudRepositorySalones;

    public ServiceSalonesImpl(RepositorySalones crudRepositorySalones){
        this.crudRepositorySalones = crudRepositorySalones;
    }

    @Override
    public List<Salones> listar() {
        return this.crudRepositorySalones.listar();
    }

    @Override
    public Salones buscar(String referenciaSalon)throws SalonesNullException {
        Salones salon = this.crudRepositorySalones.buscar(referenciaSalon);
        if(salon != null){
            return salon;
        }else{
            throw new SalonesNullException("NO");
        }
    }

    @Override
    public void crear(Salones salon) {
        this.crudRepositorySalones.crear(salon);;
    }

    @Override
    public void editar(Salones salon) {
        this.crudRepositorySalones.editar(salon);
    }

    @Override
    public void eliminar(Salones salon) {
        this.crudRepositorySalones.eliminar(salon);
    }
    
}

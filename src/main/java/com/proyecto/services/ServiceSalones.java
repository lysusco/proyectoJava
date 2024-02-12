package com.proyecto.services;

import java.util.List;

import com.proyecto.exceptiones.salonesexceptions.SalonesNullException;
import com.proyecto.repositories.models.Salones;

public interface ServiceSalones {

    List<Salones> listar();

    Salones buscar(String referenciaSalon) throws SalonesNullException;
    
    void crear(Salones salon);
    
    void editar(Salones salon);
    
    void eliminar(Salones salon);
}

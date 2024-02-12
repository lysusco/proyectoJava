package com.proyecto.services;

import java.util.List;

import com.proyecto.exceptiones.horariosexceptions.HorariosNullException;
import com.proyecto.repositories.models.Horarios;

public interface ServiceHorarios{

    List<Horarios> listar();

    Horarios buscar(String dia)throws HorariosNullException;
    
    void crear(Horarios horarios);
    
    void editar(Horarios horarios);
    
    void eliminar(Horarios horarios);
}

package com.proyecto.services;

import java.util.List;

import com.proyecto.exceptiones.cursosexceptions.CursosNullException;
import com.proyecto.repositories.models.Cursos;

public interface ServiceCursos {
    
    List<Cursos> listar();

    Cursos buscar(String nomCurso)throws CursosNullException;
    
    void crear(Cursos cursos);
    
    void editar(Cursos cursos);
    
    void eliminar(Cursos cursos);
}
    

package com.proyecto.repositories.impl.implalumnos;

import java.util.List;

import com.proyecto.repositories.RepositoryAlumnos;
import com.proyecto.repositories.models.Alumnos;
import com.proyecto.utils.conexionpersistencia.conexionbdlist.ConexionBDList;

public class RepositoryAlumnosListImpl implements RepositoryAlumnos{

    ConexionBDList conexion = ConexionBDList.getConexion();

    @Override
    public List<Alumnos> listar() {
        return conexion.getListaAlumnos();
    }

    @Override
    public Alumnos buscar(String numDocumento) {
        Alumnos resultado = null;
        for (Alumnos alumno : conexion.getListaAlumnos()) {
            if(alumno.getNumDocumento().equals(numDocumento)){
                resultado = alumno;
                break;
            }
        }
        return resultado;
    }

    @Override
    public void crear(Alumnos alumno) {
        conexion.getListaAlumnos().add(alumno);
    }

    @Override
    public void editar(Alumnos alumno) {
        for (Alumnos alumnoCurrent : conexion.getListaAlumnos()) {
            if(alumnoCurrent.getNumDocumento().equals(alumno.getNumDocumento())){
                alumnoCurrent.setPNombre(alumno.getPNombre());
                alumnoCurrent.setSNombre(alumno.getSNombre());
                alumnoCurrent.setPApellido(alumno.getPApellido());
                alumnoCurrent.setSApellido(alumno.getSApellido());
                alumnoCurrent.setCiudadResidencia(alumno.getCiudadResidencia());
                alumnoCurrent.setDireccion(alumno.getDireccion());
                alumnoCurrent.setSexo(alumno.getSexo());
                break;
            }
        }
    }

    @Override
    public void eliminar(Alumnos alum) {
        for (Alumnos alumno : conexion.getListaAlumnos()) {
            if(alumno.getNumDocumento().equals(alum.getNumDocumento())){
                conexion.getListaAlumnos().remove(alumno);
                break;
            }
        }
    }
    
}

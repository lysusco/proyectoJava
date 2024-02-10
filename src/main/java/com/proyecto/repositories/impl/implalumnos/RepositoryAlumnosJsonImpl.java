package com.proyecto.repositories.impl.implalumnos;

import java.util.List;

import com.proyecto.repositories.RepositoryAlumnos;
import com.proyecto.repositories.models.Alumnos;
import com.proyecto.utils.conexionpersistencia.conexionbdlist.ConexionBDList;

public class RepositoryAlumnosJsonImpl implements RepositoryAlumnos {

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
        List<Alumnos> listAlumnos = conexion.getData(Alumnos.class);
        listAlumnos.add(alumno);
        conexion.saveData(listAlumnos);
    }

    @Override
    public void editar(Alumnos alumno) {
        List<Alumnos> listAlumnos = conexion.getData(Alumnos.class);
        boolean change = false;
        for (Alumnos alumnosCurrent : listAlumnos) {
            if (alumnosCurrent.getNumDocumento().equals(alumno.getNumDocumento())) {
                alumnosCurrent.setPNombre(alumno.getPNombre());
                alumnosCurrent.setSNombre(alumno.getSNombre());
                alumnosCurrent.setPApellido(alumno.getPApellido());
                alumnosCurrent.setSApellido(alumno.getSApellido());
                alumnosCurrent.setCiudadResidencia(alumno.getCiudadResidencia());
                alumnosCurrent.setDireccion(alumno.getDireccion());
                alumnosCurrent.setTelefono(alumno.getTelefono());
                alumnosCurrent.setFNacimiento(alumno.getFNacimiento());
                alumnosCurrent.setSexo(alumno.getSexo());
                change = true;
                break;
            }
        }
        if (change)
            conexion.saveData(listAlumnos);
    }

    @Override
    public void eliminar(Alumnos alu) {
        List<Alumnos> listAlumnos = conexion.getData(Alumnos.class);
        boolean change = false;
        for (Alumnos alumno : listAlumnos) {
            if (alumno.getNumDocumento().equals(alu.getNumDocumento())) {
                listAlumnos.remove(alumno);
                change = true;
                break;
            }

        }
        if (change)
            conexion.saveData(listAlumnos);
    }
}
    

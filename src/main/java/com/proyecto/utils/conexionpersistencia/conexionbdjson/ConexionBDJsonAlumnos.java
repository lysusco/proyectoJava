package com.proyecto.utils.conexionpersistencia.conexionbdjson;

import com.proyecto.repositories.models.Alumnos;

public class ConexionBDJsonAlumnos extends ConexionBDJsonBase<Alumnos>{

    private static ConexionBDJsonAlumnos conexcionAlumnos;

    protected ConexionBDJsonAlumnos() {
        super("alumnos.json");
    }
    public static ConexionBDJsonAlumnos getConexion() {
        if (conexcionAlumnos != null) {
            return conexcionAlumnos;
        } else {
            conexcionAlumnos = new ConexionBDJsonAlumnos();
            return conexcionAlumnos;
        }
    }    
}

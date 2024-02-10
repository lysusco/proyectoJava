package com.proyecto.utils.conexionpersistencia.conexionbdlist;

import java.util.ArrayList;
import java.util.List;

import com.proyecto.repositories.models.Alumnos;

import lombok.Data;

@Data
public class ConexionBDList {
    private static ConexionBDList conexion;
    private List<Alumnos> listaAlumnos;

    private ConexionBDList() {
        listaAlumnos = new ArrayList<>();
        this.loadData();
    }

    public static ConexionBDList getConexion(){
        if (conexion != null) {
            return conexion;
        } else {
            conexion = new ConexionBDList();
            return conexion;
        }
    }

    private void loadData(){
        getLoadDataAlumnos();
    }

    private void getLoadDataAlumnos(){
        listaAlumnos.add(new Alumnos(1, "Cedula", "1095835397", "Luis", "Ernesto", "Corredor", "Jimenez", "Bucaramanga", "calle 27 #6-77", "3023623371", "01-10-1997", "Masculino"));
    }

    public void saveData(List<Alumnos> listAlumnos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveData'");
    }

    public List<Alumnos> getData(Class<Alumnos> class1) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getData'");
    }
}

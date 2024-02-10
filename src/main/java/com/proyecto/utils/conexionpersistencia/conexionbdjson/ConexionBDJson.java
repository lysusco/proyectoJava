package com.proyecto.utils.conexionpersistencia.conexionbdjson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.proyecto.repositories.models.Alumnos;

import lombok.Data;

@Data

public class ConexionBDJson {
    private static ConexionBDJson conexion;
    private List<Alumnos> listAlumnos;

    private ConexionBDJson() {
        listAlumnos = new ArrayList<>();
       
    }

    public static ConexionBDJson getConexion() {
        if (conexion != null) {
            return conexion;
        } else {
            conexion = new ConexionBDJson();
            return conexion;
        }
    }

    public List<Alumnos> getDataAlumnos() {
        ObjectMapper objectMapper=new ObjectMapper();            
            try{
                listAlumnos=objectMapper.readValue(new File("alumnos.json"), new TypeReference<List<Alumnos>>(){});
            }catch(IOException e){
               e.printStackTrace();
            }
            return listAlumnos;
    }
    public void saveDataClientes(List<Alumnos> listAlumnosUpdate){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            objectMapper.writeValue(new File("alumnos.json"), listAlumnosUpdate);
            System.out.println("Se guardo los alumnos en alumnos.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

}

}
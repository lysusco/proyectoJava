package com.proyecto.repositories.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class Salones {
    private int id_salon;
    private String referenciaSalon;
    private String cupoSalon;
    private String ubicacion;

    public Salones(String referenciaSalon, String cupoSalon, String ubicacion) {
        this.referenciaSalon = referenciaSalon;
        this.cupoSalon = cupoSalon;
        this.ubicacion = ubicacion;
    }

    public void imprimirSalon(){
        System.out.println("Referencia salon: "+this.getReferenciaSalon()+" Cupo: "+this.getCupoSalon()+" Ubicacion: "+this.getUbicacion());
    }

}

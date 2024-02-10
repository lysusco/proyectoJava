package com.proyecto.repositories.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class Salones {
    private String referenciaSalon;
    private int cupoSalon;
    private String ubicacion;

    public Salones(String referenciaSalon, int cupoSalon, String ubicacion) {
        this.referenciaSalon = referenciaSalon;
        this.cupoSalon = cupoSalon;
        this.ubicacion = ubicacion;
    }

}

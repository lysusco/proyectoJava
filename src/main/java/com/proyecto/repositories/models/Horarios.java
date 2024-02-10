package com.proyecto.repositories.models;

import java.time.LocalTime;

import lombok.Data;
import lombok.NoArgsConstructor;

enum Dias {
    Lunes,
    Martes,
    Miercoles,
    Jueves,
    Viernes,
    Sabado,
    Domingo
}

@Data
@NoArgsConstructor

public class Horarios {
    private Dias dia;
    private LocalTime horaIni;
    private LocalTime horaFin;

    public Horarios(Dias dia, LocalTime horaIni, LocalTime horaFin) {
        this.dia = dia;
        this.horaIni = horaIni;
        this.horaFin = horaFin;
    }

}

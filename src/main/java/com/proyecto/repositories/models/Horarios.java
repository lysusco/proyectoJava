package com.proyecto.repositories.models;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class Horarios {
    private int id;
    private String dia;
    private String horaIni;
    private String horaFin;

    public Horarios(String dia, String horaIni, String horaFin) {
        this.dia = dia;
        this.horaIni = horaIni;
        this.horaFin = horaFin;
    }

    public void imprimirHorario(){
        System.out.println("Dia: "+this.getDia()+" Hora inicio: "+this.getHoraIni()+" Hora fin: "+this.getHoraFin());
    }

}

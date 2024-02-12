package com.proyecto.repositories.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Tarifas {
    private int id_tarifa;
    private Programas programa;
    private Periodos periodo;
    private String valorCredito;

    public Tarifas(Programas programa, Periodos periodo, String valorCredito) {
        this.programa = programa;
        this.periodo = periodo;
        this.valorCredito = valorCredito;
    }

    

    public Tarifas(String valorCredito) {
        this.valorCredito = valorCredito;
    }

    public String generarNombreTarifa() {
        String nombrePrograma = "";
        if (this.programa != null) {
            nombrePrograma = this.programa.getNombreTarifaPro(); // Corregido aquí
        }
        return nombrePrograma;
    }
    

    public void imprimirTarifas(){
        if (this.programa != null) {
            System.out.println("Programa: " + this.programa.getNomPrograma());
        } else {
            System.out.println("Programa: (No definido)"); // Otra opción si el programa es null
        }
        if (this.periodo != null) {
            System.out.println("Periodo: " + this.periodo.getCodPeriodo());
        } else {
            System.out.println("Periodo: (No definido)"); // Otra opción si el periodo es null
        }
        System.out.println("Valor credito(cada uno): " + this.getValorCredito());
    }
    
}

package com.proyecto.repositories.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Tarifas {
    private int id;
    private String valorCredito;

    public Tarifas(String valorCredito) {
        this.valorCredito = valorCredito;
    }

    public void imprimirTarifas(){
        System.out.println("Tarifa valor(por credito): "+this.getValorCredito());
    }
}

package com.proyecto.repositories.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Tarifas {
    private int valorCredito;

    public Tarifas(int valorCredito) {
        this.valorCredito = valorCredito;
    }
}

package com.proyecto.repositories.models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

enum TipoDocumento {
    CC,
    TI
}

enum Sexo {
    Femenino,
    Masculino
}

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Personas {
    private int id;
    private TipoDocumento tipoDoc;
    private String numDocumento;
    private String pNombre;
    private String sNombre;
    private String pApellido;
    private String sApellido;
    private String ciudadResidencia;
    private String direccion;
    private String telefono;
    private Date fNacimiento;
    private Sexo sexo;

    public Personas(TipoDocumento tipoDoc, String numDocumento, String pNombre, String sNombre, String pApellido,
            String sApellido, String ciudadResidencia, String direccion, String telefono, Date fNacimiento, Sexo sexo) {
        this.tipoDoc = tipoDoc;
        this.numDocumento = numDocumento;
        this.pNombre = pNombre;
        this.sNombre = sNombre;
        this.pApellido = pApellido;
        this.sApellido = sApellido;
        this.ciudadResidencia = ciudadResidencia;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fNacimiento = fNacimiento;
        this.sexo = sexo;
    }

    public void imprimirPersona(){
        System.out.println("Tipo de documento: "+this.getTipoDoc());
        System.out.println("Numero documento: "+this.getNumDocumento());
        System.out.println("Nombres: "+this.getPNombre()+" "+this.getSNombre()+" "+this.getPApellido()+" "+this.getSApellido());
        System.out.println("Cuidad residencia: "+this.getDireccion());
        System.out.println("Telefono: "+this.getTelefono());
        System.out.println("Fecha de nacimiento: "+this.getFNacimiento());
        System.out.println("Sexo: "+this.getSexo());
    }
}

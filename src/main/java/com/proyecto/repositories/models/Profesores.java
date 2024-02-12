package com.proyecto.repositories.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Profesores {
    private int id_profesor;
    private String tipoDoc;
    private String numDocumento;
    private String pNombre;
    private String sNombre;
    private String pApellido;
    private String sApellido;
    private String ciudadResidencia;
    private String direccion;
    private String telefono;
    private String fNacimiento;
    private String sexo;
    private Departamentos id_departamento;

    public Profesores(String tipoDoc, String numDocumento, String pNombre, String sNombre, String pApellido,
            String sApellido, String ciudadResidencia, String direccion, String telefono, String fNacimiento,
            String sexo) {
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

    public Profesores(String tipoDoc, String numDocumento, String pNombre, String sNombre, String pApellido,
            String sApellido, String ciudadResidencia, String direccion, String telefono, String fNacimiento,
            String sexo, Departamentos id_departamento) {
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
        this.id_departamento = id_departamento;
    }

    public Departamentos obtenerDepartamento() {
        return this.id_departamento;
    }

    public Integer getId_departamento(){
        if (this.id_departamento != null) {
            return this.id_departamento.getId_departamento();
        } else {
            return null;
        }
    }

    public void setId_departamento(Departamentos departamento) {
        this.id_departamento = departamento;
    }

    public String getNombreCompleto() {
        String nombreCompleto = this.pNombre + " ";
        if (this.sNombre != null && !this.sNombre.isEmpty()) {
            nombreCompleto += this.sNombre + " ";
        }
        nombreCompleto += this.pApellido + " ";
        if (this.sApellido != null && !this.sApellido.isEmpty()) {
            nombreCompleto += this.sApellido;
        }
        return nombreCompleto;
    }
    


public void imprimirProfesor() {
    System.out.println("\u001B[34m════════════════════════════════════════");
    System.out.println("\u001B[30m           Datos del Profesor            \u001B[34m");
    System.out.println("════════════════════════════════════════");
    System.out.println(" Tipo de documento: " + this.getTipoDoc());
    System.out.println(" Numero documento: " + this.getNumDocumento());
    System.out.println(" Nombres: " + this.getNombreCompleto());
    System.out.println(" Ciudad residencia: " + this.getDireccion());
    System.out.println(" Telefono: " + this.getTelefono());
    System.out.println(" Fecha de nacimiento: " + this.getFNacimiento());
    System.out.println(" Sexo: " + this.getSexo());
    System.out.println("════════════════════════════════════════\u001B[0m");
}

}

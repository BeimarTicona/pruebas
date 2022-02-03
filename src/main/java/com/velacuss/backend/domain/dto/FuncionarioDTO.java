package com.velacuss.backend.domain.dto;

import java.time.LocalDate;

public class FuncionarioDTO {
    private  Long id;
    private  String nombre;
    private  String primerApellido;
    private  String segundoApellido;
    private  String genero;
    private  String docIdentidad;
    private  String docTipo;
    private  String docExp;
    private  String cargo;
    private  LocalDate fechaNac;
    private  String correo;
    private  String telefono;
    private  String celular;
    private String direccion;
    private LocalDate fechaIngreso;

    public FuncionarioDTO() {
    }

    public FuncionarioDTO(Long id, String nombre, String primerApellido, String segundoApellido, String genero, String docIdentidad, String docTipo, String docExp, String cargo, LocalDate fechaNac, String correo, String telefono, String celular, String direccion, LocalDate fechaIngreso) {
        this.id = id;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.genero = genero;
        this.docIdentidad = docIdentidad;
        this.docTipo = docTipo;
        this.docExp = docExp;
        this.cargo = cargo;
        this.fechaNac = fechaNac;
        this.correo = correo;
        this.telefono = telefono;
        this.celular = celular;
        this.direccion = direccion;
        this.fechaIngreso = fechaIngreso;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDocIdentidad() {
        return docIdentidad;
    }

    public void setDocIdentidad(String docIdentidad) {
        this.docIdentidad = docIdentidad;
    }

    public String getDocTipo() {
        return docTipo;
    }

    public void setDocTipo(String docTipo) {
        this.docTipo = docTipo;
    }

    public String getDocExp() {
        return docExp;
    }

    public void setDocExp(String docExp) {
        this.docExp = docExp;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public LocalDate getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(LocalDate fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    @Override
    public String toString() {
        return "FuncionarioDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", primerApellido='" + primerApellido + '\'' +
                ", segundoApellido='" + segundoApellido + '\'' +
                ", genero='" + genero + '\'' +
                ", docIdentidad='" + docIdentidad + '\'' +
                ", docTipo='" + docTipo + '\'' +
                ", docExp='" + docExp + '\'' +
                ", cargo='" + cargo + '\'' +
                ", fechaNac=" + fechaNac +
                ", correo='" + correo + '\'' +
                ", telefono='" + telefono + '\'' +
                ", celular='" + celular + '\'' +
                ", direccion='" + direccion + '\'' +
                ", fechaIngreso=" + fechaIngreso +
                '}';
    }
}

package com.velacuss.backend.domain;

import java.time.LocalDate;
import java.util.Objects;

public class Funcionario {
    private Long id;
    private Long logtransId;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String genero;
    private String docIdentidad;
    private String docTipo;
    private String docExp;
    private String cargo;
    private LocalDate fechaNac;
    private String correo;
    private String telefono;
    private String celular;
    private String direccion;
    private LocalDate fechaIngreso;
    private String estado;

    public Funcionario() {
    }

    public Funcionario(Long id, Long logtransId, String nombre, String primerApellido, String segundoApellido, String genero, String docIdentidad, String docTipo, String docExp, String cargo, LocalDate fechaNac, String correo, String telefono, String celular, String direccion, LocalDate fechaIngreso, String estado) {
        this.id = id;
        this.logtransId = logtransId;
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
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLogtransId() {
        return logtransId;
    }

    public void setLogtransId(Long logtransId) {
        this.logtransId = logtransId;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "id=" + id +
                ", logtransId=" + logtransId +
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
                ", estado='" + estado + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(!(obj instanceof Funcionario)) {
            return false;
        }
        Funcionario e = (Funcionario) obj;
        return
                Objects.equals(nombre, e.nombre) &&
                Objects.equals(primerApellido, e.primerApellido) &&
                Objects.equals(segundoApellido, e.segundoApellido) &&
                Objects.equals(genero, e.genero) &&
                Objects.equals(docIdentidad, e.docIdentidad) &&
                Objects.equals(docTipo, e.docTipo) &&
                Objects.equals(docExp, e.docExp) &&
                Objects.equals(cargo, e.cargo) &&
                Objects.equals(fechaNac, e.fechaNac) &&
                Objects.equals(correo, e.correo) &&
                Objects.equals(telefono, e.telefono) &&
                Objects.equals(celular, e.celular) &&
                Objects.equals(direccion, e.direccion) &&
                Objects.equals(fechaIngreso, e.fechaIngreso);
    }
}

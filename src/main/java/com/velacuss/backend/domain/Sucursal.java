package com.velacuss.backend.domain;

import java.util.Objects;

public class Sucursal {
    private Long id;
    private Long logtransId;
    private String tipo;
    private String numero;
    private String nombre;
    private String direccion;
    private String telefono;
    private String celular;
    private String fax;
    private String ciudad;
    private String departamento;
    private String pais;
    private String estado;

    public Sucursal() {
    }

    public Sucursal(Long id, Long logtransId, String tipo, String numero, String nombre, String direccion, String telefono, String celular, String fax, String ciudad, String departamento, String pais, String estado) {
        this.id = id;
        this.logtransId = logtransId;
        this.tipo = tipo;
        this.numero = numero;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.celular = celular;
        this.fax = fax;
        this.ciudad = ciudad;
        this.departamento = departamento;
        this.pais = pais;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Sucursal{" +
                "id=" + id +
                ", logtransId=" + logtransId +
                ", tipo='" + tipo + '\'' +
                ", numero='" + numero + '\'' +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", celular='" + celular + '\'' +
                ", fax='" + fax + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", departamento='" + departamento + '\'' +
                ", pais='" + pais + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(!(obj instanceof Linea)) {
            return false;
        }
        Sucursal e = (Sucursal) obj;
        return
                Objects.equals(tipo, e.tipo) &&
                Objects.equals(numero, e.numero) &&
                Objects.equals(nombre, e.nombre) &&
                Objects.equals(direccion, e.direccion) &&
                Objects.equals(telefono, e.telefono) &&
                Objects.equals(celular, e.celular) &&
                Objects.equals(fax, e.fax) &&
                Objects.equals(ciudad, e.ciudad) &&
                Objects.equals(departamento, e.departamento) &&
                Objects.equals(pais, e.pais);
    }
}

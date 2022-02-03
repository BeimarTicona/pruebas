package com.velacuss.backend.domain;

import java.time.LocalDate;

public class Dominio {

    Long id;
    String dominio;
    String codigo;
    String nombre;
    String orden;
    String descripcion;
    String subdominio;
    LocalDate fecha_inicio;
    LocalDate fecha_fin;
    String estado;

    public Dominio() {
    }

    public Dominio(Long id, String dominio, String codigo, String nombre, String orden, String descripcion, String subdominio, LocalDate fecha_inicio, LocalDate fecha_fin, String estado) {
        this.id = id;
        this.dominio = dominio;
        this.codigo = codigo;
        this.nombre = nombre;
        this.orden = orden;
        this.descripcion = descripcion;
        this.subdominio = subdominio;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDominio() {
        return dominio;
    }

    public void setDominio(String dominio) {
        this.dominio = dominio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSubdominio() {
        return subdominio;
    }

    public void setSubdominio(String subdominio) {
        this.subdominio = subdominio;
    }

    public LocalDate getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(LocalDate fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public LocalDate getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(LocalDate fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Dominio{" +
                "id=" + id +
                ", dominio='" + dominio + '\'' +
                ", codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", orden='" + orden + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", subdominio='" + subdominio + '\'' +
                ", fecha_inicio=" + fecha_inicio +
                ", fecha_fin=" + fecha_fin +
                ", estado='" + estado + '\'' +
                '}';
    }
}

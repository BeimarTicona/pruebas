package com.velacuss.backend.domain;

import java.util.Objects;

public class Linea {
    private Long id;
    private Long logtransId;
    private String nombre;
    private String estado;

    public Linea() {
    }

    public Linea(Long id, Long logtransId, String nombre, String estado) {
        this.id = id;
        this.logtransId = logtransId;
        this.nombre = nombre;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Linea{" +
                "id=" + id +
                ", logtransId=" + logtransId +
                ", nombre='" + nombre + '\'' +
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
        Linea e = (Linea) obj;
        return
                Objects.equals(nombre, e.nombre);
    }
}

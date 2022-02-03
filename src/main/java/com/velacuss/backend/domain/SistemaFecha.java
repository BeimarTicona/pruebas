package com.velacuss.backend.domain;

import java.util.Date;

public class SistemaFecha {

    Long id;
    Long logTransId;
    Long sucursalId;
    Date fecha;
    String estado;


    public SistemaFecha() {
    }

    public SistemaFecha(Long id, Long logTransId, Long sucursalId, Date fecha, String estado) {
        this.id = id;
        this.logTransId = logTransId;
        this.sucursalId = sucursalId;
        this.fecha = fecha;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLogTransId() {
        return logTransId;
    }

    public void setLogTransId(Long logTransId) {
        this.logTransId = logTransId;
    }

    public Long getSucursalId() {
        return sucursalId;
    }

    public void setSucursalId(Long sucursalId) {
        this.sucursalId = sucursalId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "SistemaFecha{" +
                "id=" + id +
                ", logTransId=" + logTransId +
                ", sucursalId=" + sucursalId +
                ", fecha=" + fecha +
                ", estado='" + estado + '\'' +
                '}';
    }
}
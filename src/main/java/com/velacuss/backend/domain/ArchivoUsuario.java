package com.velacuss.backend.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class ArchivoUsuario {
    private Long id;
    private Long logtransId;
    private Long usuarioId;
    private String url;
    private String nombre;
    private String mimetype;
    private Integer tamanio;
    private Boolean principal;
    private Integer orden;
    private LocalDateTime fecha;
    private String estado;

    public ArchivoUsuario() {
    }

    public ArchivoUsuario(Long id, Long logtransId, Long usuarioId, String url, String nombre, String mimetype, Integer tamanio, Boolean principal, Integer orden, LocalDateTime fecha, String estado) {
        this.id = id;
        this.logtransId = logtransId;
        this.usuarioId = usuarioId;
        this.url = url;
        this.nombre = nombre;
        this.mimetype = mimetype;
        this.tamanio = tamanio;
        this.principal = principal;
        this.orden = orden;
        this.fecha = fecha;
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

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMimetype() {
        return mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    public Integer getTamanio() {
        return tamanio;
    }

    public void setTamanio(Integer tamanio) {
        this.tamanio = tamanio;
    }

    public Boolean getPrincipal() {
        return principal;
    }

    public void setPrincipal(Boolean principal) {
        this.principal = principal;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
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
        return "ArchivoUsuario{" +
                "id=" + id +
                ", logtransId=" + logtransId +
                ", usuarioId=" + usuarioId +
                ", url='" + url + '\'' +
                ", nombre='" + nombre + '\'' +
                ", mimetype='" + mimetype + '\'' +
                ", tamanio=" + tamanio +
                ", principal=" + principal +
                ", orden=" + orden +
                ", fecha=" + fecha +
                ", estado='" + estado + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(!(obj instanceof ArchivoUsuario)) {
            return false;
        }
        ArchivoUsuario e = (ArchivoUsuario) obj;
        return
                Objects.equals(usuarioId, e.usuarioId) &&
                Objects.equals(url, e.url) &&
                Objects.equals(nombre, e.nombre) &&
                Objects.equals(mimetype, e.mimetype) &&
                Objects.equals(tamanio, e.tamanio) &&
                Objects.equals(principal, e.principal) &&
                Objects.equals(orden, e.orden);
    }
}

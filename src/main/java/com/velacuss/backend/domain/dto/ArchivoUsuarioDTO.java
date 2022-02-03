package com.velacuss.backend.domain.dto;

public class ArchivoUsuarioDTO {
    private Long id;
    private Long usuarioId;
    private String url;
    private String nombre;
    private String mimetype;
    private Integer tamanio;
    private Boolean principal;
    private Integer orden;

    public ArchivoUsuarioDTO() {
    }

    public ArchivoUsuarioDTO(Long id, Long usuarioId, String url, String nombre, String mimetype, Integer tamanio,
            Boolean principal, Integer orden) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.url = url;
        this.nombre = nombre;
        this.mimetype = mimetype;
        this.tamanio = tamanio;
        this.principal = principal;
        this.orden = orden;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "ArchivoUsuarioDTO{" +
                "id=" + id +
                ", usuarioId='" + usuarioId + '\'' +
                ", url='" + url + '\'' +
                ", nombre='" + nombre + '\'' +
                ", mimetype='" + mimetype + '\'' +
                ", tamanio=" + tamanio +
                ", principal=" + principal +
                ", orden=" + orden +
                '}';
    }
}

package com.velacuss.backend.domain.dto;

public class RolDTO {
    private Long id;
    private String sigla;
    private String descripcion;

    public RolDTO() {
    }

    public RolDTO(Long id, String sigla, String descripcion) {
        this.id = id;
        this.sigla = sigla;
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "RolDTO{" +
                "id=" + id +
                ", sigla='" + sigla + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}

package com.velacuss.backend.domain.dto;

import java.util.List;

public class UsuarioInicioDTO {
    private List<DominioDTO> generos;
    private List<DominioDTO> documentos;
    private List<DominioDTO> departamentos;
    private List<SucursalDTO> sucursales;
    private List<RolDTO> roles;

    public UsuarioInicioDTO() {
    }

    public UsuarioInicioDTO(List<DominioDTO> generos, List<DominioDTO> documentos, List<DominioDTO> departamentos, List<SucursalDTO> sucursales, List<RolDTO> roles) {
        this.generos = generos;
        this.documentos = documentos;
        this.departamentos = departamentos;
        this.sucursales = sucursales;
        this.roles = roles;
    }

    public List<DominioDTO> getGeneros() {
        return generos;
    }

    public void setGeneros(List<DominioDTO> generos) {
        this.generos = generos;
    }

    public List<DominioDTO> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<DominioDTO> documentos) {
        this.documentos = documentos;
    }

    public List<DominioDTO> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<DominioDTO> departamentos) {
        this.departamentos = departamentos;
    }

    public List<SucursalDTO> getSucursales() {
        return sucursales;
    }

    public void setSucursales(List<SucursalDTO> sucursales) {
        this.sucursales = sucursales;
    }

    public List<RolDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RolDTO> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UsuarioInicioDTO{" +
                "generos=" + generos +
                ", documentos=" + documentos +
                ", departamentos=" + departamentos +
                ", sucursales=" + sucursales +
                ", roles=" + roles +
                '}';
    }
}

package com.velacuss.backend.domain.dto;

import java.util.List;

public class SucursalInicioDTO {
    private List<DominioDTO> paises;
    private List<DominioDTO> departamentos;
    private List<DominioDTO> tiposSucursal;

    public SucursalInicioDTO() {
    }

    public SucursalInicioDTO(List<DominioDTO> paises, List<DominioDTO> departamentos, List<DominioDTO> tiposSucursal) {
        this.paises = paises;
        this.departamentos = departamentos;
        this.tiposSucursal = tiposSucursal;
    }

    public List<DominioDTO> getPaises() {
        return paises;
    }

    public void setPaises(List<DominioDTO> paises) {
        this.paises = paises;
    }

    public List<DominioDTO> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<DominioDTO> departamentos) {
        this.departamentos = departamentos;
    }

    public List<DominioDTO> getTiposSucursal() {
        return tiposSucursal;
    }

    public void setTiposSucursal(List<DominioDTO> tiposSucursal) {
        this.tiposSucursal = tiposSucursal;
    }

    @Override
    public String toString() {
        return "SucursalInicioDTO{" +
                "paises=" + paises +
                ", departamentos=" + departamentos +
                ", tiposSucursal=" + tiposSucursal +
                '}';
    }
}

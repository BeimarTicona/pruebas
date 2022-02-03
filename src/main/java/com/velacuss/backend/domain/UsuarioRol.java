package com.velacuss.backend.domain;

public class UsuarioRol {
    private Long id;
    private Long usuarioId;
    private Long rolId;
    private String estado;

    public UsuarioRol() {
    }

    public UsuarioRol(Long id, Long usuarioId, Long rolId, String estado) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.rolId = rolId;
        this.estado = estado;
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

    public Long getRolId() {
        return rolId;
    }

    public void setRolId(Long rolId) {
        this.rolId = rolId;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "UsuarioRol{" +
                "id=" + id +
                ", usuarioId=" + usuarioId +
                ", rolId=" + rolId +
                ", estado='" + estado + '\'' +
                '}';
    }
}

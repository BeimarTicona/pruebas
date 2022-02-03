package com.velacuss.backend.domain;

import java.util.Objects;

public class Usuario {
    private Long id;
    private Long logtransId;
    private Long funcionarioId;
    private Long sucursalId;
    private String login;
    private String clave;
    private Boolean enabled;
    private Integer intentos;
    private String estado;

    public Usuario() {
    }

    public Usuario(Long id, Long logtransId, Long funcionarioId, Long sucursalId, String login, String clave, Boolean enabled, Integer intentos, String estado) {
        this.id = id;
        this.logtransId = logtransId;
        this.funcionarioId = funcionarioId;
        this.sucursalId = sucursalId;
        this.login = login;
        this.clave = clave;
        this.enabled = enabled;
        this.intentos = intentos;
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

    public Long getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(Long funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    public Long getSucursalId() {
        return sucursalId;
    }

    public void setSucursalId(Long sucursalId) {
        this.sucursalId = sucursalId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getIntentos() {
        return intentos;
    }

    public void setIntentos(Integer intentos) {
        this.intentos = intentos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", logtransId=" + logtransId +
                ", funcionarioId=" + funcionarioId +
                ", sucursalId=" + sucursalId +
                ", login='" + login + '\'' +
                ", clave='" + clave + '\'' +
                ", enabled=" + enabled +
                ", intentos=" + intentos +
                ", estado='" + estado + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(!(obj instanceof Usuario)) {
            return false;
        }
        Usuario e = (Usuario) obj;
        return
                Objects.equals(sucursalId, e.sucursalId) &&
                Objects.equals(login, e.login);
    }
}

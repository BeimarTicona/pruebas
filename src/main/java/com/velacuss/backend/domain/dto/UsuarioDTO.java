package com.velacuss.backend.domain.dto;

public class UsuarioDTO {
    private Long id;
    private Long sucursalId;
    private Long rolId;
    private String login;
    private String clave;
    private Boolean enabled;
    private FuncionarioDTO funcionario;
    private ArchivoUsuarioDTO archivo;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Long id, Long sucursalId, Long rolId, String login, String clave, Boolean enabled, FuncionarioDTO funcionario, ArchivoUsuarioDTO archivo) {
        this.id = id;
        this.sucursalId = sucursalId;
        this.rolId = rolId;
        this.login = login;
        this.clave = clave;
        this.enabled = enabled;
        this.funcionario = funcionario;
        this.archivo = archivo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSucursalId() {
        return sucursalId;
    }

    public void setSucursalId(Long sucursalId) {
        this.sucursalId = sucursalId;
    }

    public Long getRolId() {
        return rolId;
    }

    public void setRolId(Long rolId) {
        this.rolId = rolId;
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

    public FuncionarioDTO getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(FuncionarioDTO funcionario) {
        this.funcionario = funcionario;
    }

    public ArchivoUsuarioDTO getArchivo() {
        return archivo;
    }

    public void setArchivo(ArchivoUsuarioDTO archivo) {
        this.archivo = archivo;
    }

    @Override
    public String toString() {
        return "UsuarioDTO{" +
                "id=" + id +
                ", sucursalId=" + sucursalId +
                ", rolId=" + rolId +
                ", login='" + login + '\'' +
                ", clave='" + clave + '\'' +
                ", enabled=" + enabled +
                ", funcionario=" + funcionario +
                ", archivo=" + archivo +
                '}';
    }
}

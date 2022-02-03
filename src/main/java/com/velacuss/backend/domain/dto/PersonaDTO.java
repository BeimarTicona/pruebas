package com.velacuss.backend.domain.dto;

public class PersonaDTO {
    private Long id;
    private Long usuarioId;
    private String nombre;
    private String apellido;
    private UsuarioDTO usuario;


    @Override
    public String toString() {
        return "PersonaDTO{" +
                "id=" + id +
                ", usuarioId=" + usuarioId +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", usuario=" + usuario +
                '}';
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public PersonaDTO(){

    }

    public PersonaDTO(Long id, Long usuarioId, String nombre, String apellido, UsuarioDTO usuario) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

}

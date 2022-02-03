package com.velacuss.backend.domain;

public class Persona {
    private Long id;
    private Long logtransId;
    private Long usuarioId;
    private String nombre;
    private String apellido;
    private String estado;

    public Persona(){

    }

   public Persona(Long id, Long logtransId, Long usuarioId, String nombre, String apellido, String estado){
        this.id=id;
        this.logtransId=logtransId;
        this.usuarioId=usuarioId;
        this.nombre=nombre;
        this.apellido=apellido;
        this.estado=estado;
   }

    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", logtransId=" + logtransId +
                ", usuarioId=" + usuarioId +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", estado='" + estado + '\'' +
                '}';
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

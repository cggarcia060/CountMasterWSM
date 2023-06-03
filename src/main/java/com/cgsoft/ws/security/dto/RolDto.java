package com.cgsoft.ws.security.dto;

import jakarta.validation.constraints.NotBlank;

public class RolDto {

    public int id;
    @NotBlank(message="Nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "Codigo es requerido")
    private String rolNombre;

    public RolDto(String nombre, String rolNombre) {
        this.nombre = nombre;
        this.rolNombre = rolNombre;
    }

    public RolDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(String rolNombre) {
        this.rolNombre = rolNombre;
    }
}

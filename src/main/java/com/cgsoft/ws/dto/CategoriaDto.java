package com.cgsoft.ws.dto;

import jakarta.validation.constraints.NotBlank;


public class CategoriaDto {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El estado es obligatorio")
    private boolean estado;

    public CategoriaDto(String nombre, boolean estado) {
        this.nombre = nombre;
        this.estado = estado;
    }

    public CategoriaDto() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}

package com.cgsoft.ws.security.dto;

import jakarta.validation.constraints.NotBlank;

public class ProcesoDto {

    public int id;
    @NotBlank(message="Nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "Codigo es requerido")
    private String codigo;


    public ProcesoDto(String nombre, String codigo) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
    }

    public ProcesoDto() {
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}

package com.cgsoft.ws.security.dto;

import com.cgsoft.ws.security.entity.Proceso;
import com.cgsoft.ws.security.entity.Rol;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

public class UsuarioDto {

    private Long id;
    @NotBlank(message = "el nombre es obligatorio")
    private String nombre;
    @NotBlank(message = "el apellido es obligatorio")
    private String apellido;
    @NotBlank(message = "el nombre de usuario es obligatorio")
    private String nombreUsuario;
    @Email(message = "la dirección de email no válida")
    @NotBlank(message = "el email es obligatorio")
    private String email;
    private Set<Rol> roles = new HashSet<>();
    private Set<Proceso> procesos = new HashSet<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

    public Set<Proceso> getProcesos() {
        return procesos;
    }

    public void setProcesos(Set<Proceso> procesos) {
        this.procesos = procesos;
    }
}

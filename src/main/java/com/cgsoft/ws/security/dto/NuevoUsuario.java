package com.cgsoft.ws.security.dto;

import com.cgsoft.ws.security.entity.Proceso;
import com.cgsoft.ws.security.entity.Rol;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

public class NuevoUsuario {
    @NotBlank(message = "el nombre es obligatorio")
    private String nombre;
    @NotBlank(message = "el apellido es obligatorio")
    private String apellido;

    @NotBlank(message = "el numero de identificacion es obligatorio")
    private String identificacion;

    @NotBlank(message = "el nombre de usuario es obligatorio")
    private String nombreUsuario;
    @Email(message = "la dirección de email no válida")
    @NotBlank(message = "el email es obligatorio")
    private String email;
    @NotBlank (message = "la contraseña es obligatoria")
    private String password;
    private Set<Rol> roles = new HashSet<>();

    private Set<Proceso> procesos = new HashSet<>();


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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }
}
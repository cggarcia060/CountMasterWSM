package com.cgsoft.ws.security.dto;

import com.cgsoft.ws.security.enums.RolNombre;
import jakarta.validation.constraints.NotBlank;

public class RolAndProcesoDto {

    //@NotBlank(message="El rol es requerido")
    @NotBlank(message="El rol es requerido")
    private  String rol;
    @NotBlank(message="El proceso es requerido")
    private String proceso;

    public RolAndProcesoDto(String rol, String proceso) {
        this.rol = rol;
        this.proceso = proceso;
    }

    public RolAndProcesoDto() {
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getProceso() {
        return proceso;
    }

    public void setProceso(String proceso) {
        this.proceso = proceso;
    }
}

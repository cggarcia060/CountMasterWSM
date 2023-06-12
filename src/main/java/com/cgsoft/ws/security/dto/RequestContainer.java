package com.cgsoft.ws.security.dto;

import com.cgsoft.ws.security.entity.Proceso;
import com.cgsoft.ws.security.entity.Rol;
import lombok.Data;

@Data
public class RequestContainer {

    private Rol rol;
    private Proceso proceso;

}

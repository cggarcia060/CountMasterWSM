package com.cgsoft.ws.service;

import com.cgsoft.ws.entity.Proveedor;
import com.cgsoft.ws.security.entity.Proceso;

import java.util.List;

public interface ProveedorService {
    List<Proveedor> getListByProceso(Proceso proceso);
}

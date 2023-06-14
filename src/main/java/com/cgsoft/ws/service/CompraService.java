package com.cgsoft.ws.service;

import com.cgsoft.ws.dto.CompraDto;
import com.cgsoft.ws.dto.Mensaje;
import com.cgsoft.ws.entity.Compra;
import com.cgsoft.ws.security.entity.Proceso;

import java.util.List;

public interface CompraService {

 List<Compra> getListComprasByProceso(Proceso proceso);

 Mensaje  saveCompra(CompraDto compraDto);

}

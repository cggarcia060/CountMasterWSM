package com.cgsoft.ws.controllers;


import com.cgsoft.ws.entity.Proveedor;
import com.cgsoft.ws.security.entity.Proceso;
import com.cgsoft.ws.serviceIpml.ProveedorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proveedor")
@CrossOrigin
public class ProveedorController {


    @Autowired
    ProveedorServiceImpl proveedorService;

    @PostMapping("list")
    public ResponseEntity<List<Proveedor>> getListByProcess(@RequestBody Proceso proceso){
        return ResponseEntity.ok(proveedorService.getListByProceso(proceso));
    }

}

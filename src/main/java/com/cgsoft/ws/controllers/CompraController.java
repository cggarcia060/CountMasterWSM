package com.cgsoft.ws.controllers;


import com.cgsoft.ws.dto.CompraDto;
import com.cgsoft.ws.dto.Mensaje;
import com.cgsoft.ws.entity.Compra;
import com.cgsoft.ws.security.entity.Proceso;
import com.cgsoft.ws.serviceIpml.CompraServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ( "/compra")
@CrossOrigin
public class CompraController {


    @Autowired
    CompraServiceImpl compraService;

    @PostMapping( "list")
    public ResponseEntity<List<Compra>> getComprasByProceso(@RequestBody Proceso proceso){
        return  ResponseEntity.ok(compraService.getListComprasByProceso(proceso));
    }

    @PostMapping("save")
    public  ResponseEntity<Mensaje> saveCompra(@Valid @RequestBody CompraDto compraDto){
        return  ResponseEntity.ok(compraService.saveCompra(compraDto));
    }
    @PostMapping("update")
    public  ResponseEntity<Mensaje> updateCompra(@Valid @RequestBody CompraDto compraDto){
        return  ResponseEntity.ok(compraService.updateCompra(compraDto));
    }
    @GetMapping("delete/{id}")
    public ResponseEntity<Mensaje> delete(@PathVariable("id") Long id){
        return  ResponseEntity.ok(compraService.deleteCompra(id));
    }
}

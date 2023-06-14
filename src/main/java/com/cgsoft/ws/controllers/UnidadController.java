package com.cgsoft.ws.controllers;

import com.cgsoft.ws.entity.Unidad;
import com.cgsoft.ws.serviceIpml.UnidadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/unidad")
@CrossOrigin
public class UnidadController {


    @Autowired
    UnidadServiceImpl unidadService;

    @GetMapping("list")
    public ResponseEntity<List<Unidad>> getListUnidad(){
        return  ResponseEntity.ok(unidadService.getListUnidad());
    }

}

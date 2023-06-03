package com.cgsoft.ws.security.controller;

import com.cgsoft.ws.dto.Mensaje;
import com.cgsoft.ws.security.dto.ProcesoDto;
import com.cgsoft.ws.security.entity.Proceso;
import com.cgsoft.ws.security.service.ProcesoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/procesos")
@CrossOrigin
public class ProcesosController {

    @Autowired
    ProcesoService procesoService;

    @GetMapping("list")
    public ResponseEntity<List<Proceso>> list(){
        return ResponseEntity.ok(procesoService.listProceso());
    }

    @PostMapping("save")
    public ResponseEntity<Mensaje> save(@Valid @RequestBody ProcesoDto procesoDto){
        return  ResponseEntity.ok(procesoService.save(procesoDto));
    }
    @PostMapping("update")
    public ResponseEntity<Mensaje> update(@Valid @RequestBody ProcesoDto procesoDto){
        return  ResponseEntity.ok(procesoService.update(procesoDto));
    }
    @GetMapping("delete/{id}")
    public ResponseEntity<Mensaje> delete(@PathVariable("id") int id){
        return  ResponseEntity.ok(procesoService.delete(id));
    }

}

package com.cgsoft.ws.security.controller;


import com.cgsoft.ws.dto.Mensaje;
import com.cgsoft.ws.security.dto.PermisoDto;
import com.cgsoft.ws.security.dto.RequestContainer;
import com.cgsoft.ws.security.dto.RolAndProcesoDto;
import com.cgsoft.ws.security.entity.Permisos;
import com.cgsoft.ws.security.entity.Proceso;
import com.cgsoft.ws.security.service.PermisosService;
import com.cgsoft.ws.security.service.ProcesoService;
import com.cgsoft.ws.security.service.UsuarioService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/permisos")
@CrossOrigin
public class PermisosController {

    @Autowired
    PermisosService permisosService;

    @Autowired
    ProcesoService procesoService;

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/{id}")
    public ResponseEntity<Permisos> permisos(@PathVariable("id") int id){
        return  ResponseEntity.ok(permisosService.getPermisos(id));
    }
    @PostMapping("/process")
    public ResponseEntity<Map<String, String>> detbyProceso(@Valid @RequestBody RolAndProcesoDto rolAndProcesoDto) throws Exception {
        return  ResponseEntity.ok(permisosService.getRolesByProceso(rolAndProcesoDto));
    }
    @PostMapping("/list")
    public ResponseEntity<List<Permisos>> getAll(@RequestBody RequestContainer requestContainer){

        return  ResponseEntity.ok(permisosService.listAll(requestContainer));
    }
    @PostMapping("/save")
    public ResponseEntity<Mensaje> save(@Valid @RequestBody PermisoDto permisoDto){
        return ResponseEntity.ok(permisosService.save(permisoDto));
    }
    @PostMapping("/update")
    public ResponseEntity<Mensaje> update(@Valid @RequestBody PermisoDto permisoDto){
        return ResponseEntity.ok(permisosService.update(permisoDto));
    }
    @GetMapping("/delete/{id}")
    public ResponseEntity<Mensaje> detbyProceso(@PathVariable("id") int id) {
        return  ResponseEntity.ok(permisosService.delete(id));
    }

}

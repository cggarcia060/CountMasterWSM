package com.cgsoft.ws.security.controller;

import com.cgsoft.ws.dto.Mensaje;
import com.cgsoft.ws.security.dto.RequestContainer;
import com.cgsoft.ws.security.dto.RolDto;
import com.cgsoft.ws.security.entity.Rol;
import com.cgsoft.ws.security.service.RolService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@CrossOrigin
public class RolController {

    @Autowired
    RolService rolService;

    @PostMapping("list")
    public ResponseEntity<List<Rol>> getRoles(@RequestBody Rol rol){
     return  ResponseEntity.ok(rolService.getRoles(rol));
    }


    @PostMapping("save")
    public ResponseEntity<Mensaje> save(@Valid @RequestBody RolDto rolDto){
        return  ResponseEntity.ok(rolService.save(rolDto));
    }

    @PostMapping("update")
    public ResponseEntity<Mensaje> update(@Valid @RequestBody RolDto rolDto){
        return  ResponseEntity.ok(rolService.update(rolDto));
    }

    @GetMapping("delete/{id}")
    public ResponseEntity<Mensaje> delete(@PathVariable("id") int id){
        return  ResponseEntity.ok(rolService.delete(id));
    }
}

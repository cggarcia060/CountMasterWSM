package com.cgsoft.ws.security.controller;

import com.cgsoft.ws.dto.Mensaje;
import com.cgsoft.ws.security.dto.UsuarioDto;
import com.cgsoft.ws.security.entity.Proceso;
import com.cgsoft.ws.security.entity.Usuario;
import com.cgsoft.ws.security.service.ProcesoService;
import com.cgsoft.ws.security.service.UsuarioService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/usuarios")
@CrossOrigin
public class UsuariosController {


    @Autowired
    UsuarioService usuarioService;

    @Autowired
    ProcesoService procesoService;


    @GetMapping("/list")
    public ResponseEntity<Map<String, String>> list() throws JsonProcessingException {
        return  ResponseEntity.ok(usuarioService.listAllUsuario());
    }

    @PostMapping("/usuarioProcess")
    public ResponseEntity<Map<String, String>> getUsuariosByProcess(@RequestBody Map<String, Integer> json) throws JsonProcessingException {
        Proceso proceso= procesoService.getProcesoById(json.get("procesoId"));
        return  ResponseEntity.ok( usuarioService.getUsuariosByProcess(proceso));

    }

    @PostMapping("/delete")
    public ResponseEntity<Mensaje> delete(@RequestBody Map<String,Long> key){
        return  ResponseEntity.ok(usuarioService.delete(key.get("id")));
    }

    @PostMapping("/update")
    public ResponseEntity<Mensaje> update(@RequestBody UsuarioDto usuario){
        return  ResponseEntity.ok(usuarioService.update(usuario));
    }


}

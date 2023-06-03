package com.cgsoft.ws.security.controller;

import com.cgsoft.ws.dto.Mensaje;
import com.cgsoft.ws.security.dto.JwtDto;
import com.cgsoft.ws.security.dto.LoginUsuario;
import com.cgsoft.ws.security.dto.NuevoUsuario;
import com.cgsoft.ws.security.entity.Proceso;
import com.cgsoft.ws.security.entity.Usuario;
import com.cgsoft.ws.security.service.ProcesoService;
import com.cgsoft.ws.security.service.UsuarioService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

        @Autowired
        UsuarioService usuarioService;

        @Autowired
        ProcesoService procesoService;

        @PostMapping("/nuevo")
        public ResponseEntity<Mensaje> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario){
            return ResponseEntity.ok(usuarioService.save(nuevoUsuario));
        }

        @GetMapping("/usuario/{nombre}")
        public ResponseEntity<Usuario> getusuario(@PathVariable("nombre") String nombre){
            return ResponseEntity.ok(usuarioService.getByNombreUsuario(nombre));
        }

        @PostMapping("/login")
        public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario){
           return ResponseEntity.ok(usuarioService.login(loginUsuario));
        }

        @PostMapping("/refresh")

        public ResponseEntity<JwtDto> refresh(@RequestBody JwtDto jwtDto) throws ParseException {
            System.out.println("refresh");
            return ResponseEntity.ok(usuarioService.refresh(jwtDto));
        }

    }
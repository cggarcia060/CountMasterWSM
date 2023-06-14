package com.cgsoft.ws.controllers;

import com.cgsoft.ws.dto.CategoriaDto;
import com.cgsoft.ws.dto.Mensaje;
import com.cgsoft.ws.entity.Categoria;
import com.cgsoft.ws.serviceIpml.CategoriaServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/categoria")
@CrossOrigin
public class CategoriaController {
/*
    @Autowired
    CategoriaServiceImpl categoriaService;

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Categoria>> getAll(){
        return   ResponseEntity.ok(categoriaService.getList());
    }
    @RequestMapping(value = "/list/{state}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Categoria>> getlistaBy(@PathVariable("state") boolean state){
        return ResponseEntity.ok(categoriaService.getListByState(state));
    }

    @RequestMapping(value="/save" , method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mensaje> save(@Valid @RequestBody CategoriaDto categoriaDto ){
        return  ResponseEntity.ok(categoriaService.save(categoriaDto));
    }


    @RequestMapping(value="/update/{id}" , method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mensaje> update(@Valid @PathVariable("id") int id,@RequestBody CategoriaDto categoriaDto ){
        return ResponseEntity.ok(categoriaService.update(id,categoriaDto));
    }

    @RequestMapping(value="/delete" , method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mensaje> delete(@RequestParam int id) {
        return  ResponseEntity.ok(categoriaService.delete(id));
    }

*/
}

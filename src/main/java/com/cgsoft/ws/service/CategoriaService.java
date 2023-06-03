package com.cgsoft.ws.service;

import com.cgsoft.ws.dto.CategoriaDto;
import com.cgsoft.ws.dto.Mensaje;
import com.cgsoft.ws.entity.Categoria;

import java.util.List;

public interface CategoriaService {
    List<Categoria> getList();
    List<Categoria> getListByState(boolean estado);
    Mensaje save(CategoriaDto categoriaDto);
    Mensaje update(int id ,CategoriaDto categoriaDto);
    Categoria getById(int id);
    Mensaje delete(int id);
    Categoria getByNombre(String nombre);

}

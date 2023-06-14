package com.cgsoft.ws.serviceIpml;

import com.cgsoft.ws.dto.CategoriaDto;
import com.cgsoft.ws.dto.Mensaje;
import com.cgsoft.ws.entity.Categoria;
import com.cgsoft.ws.exceptions.CustomException;
import com.cgsoft.ws.repository.CategoriaRepository;
import com.cgsoft.ws.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoriaServiceImpl  {
/*
    @Autowired
    CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> getList() {
        return categoriaRepository.findAll();
    }

    @Override
    public List<Categoria> getListByState(boolean estado) {
        return categoriaRepository.findByEstado(estado);
    }


    @Override
    public Mensaje save(CategoriaDto categoriaDto) {
        if(categoriaRepository.existsByNombre(categoriaDto.getNombre()))
            throw  new CustomException(HttpStatus.BAD_REQUEST,"Ese nombre de categoria ya existe");
        Categoria categoria=new Categoria(categoriaDto.getNombre(),categoriaDto.isEstado());
        categoriaRepository.save(categoria);
        return new Mensaje(categoria.getNombre() + "a sido creada exitosamente");
    }

    public Mensaje update(int id ,CategoriaDto categoriaDto){
        if(!categoriaRepository.existsById(id))
            throw new CustomException (HttpStatus.NOT_FOUND,"esa categoria no existe");
        Optional<Categoria> optionalCategoria=categoriaRepository.findByNombre(categoriaDto.getNombre());
        if(optionalCategoria.isPresent() && optionalCategoria.get().getId() != id)
            throw  new CustomException(HttpStatus.BAD_REQUEST,"Ese nombre de categoria ya existe");
        Categoria categoria= getById(id);
        categoria.setNombre(categoriaDto.getNombre());
        categoria.setEstado(categoriaDto.isEstado());
        categoriaRepository.save(categoria);
        return new Mensaje(categoria.getNombre() + "a sido actualizado exitosamente");

    }

    @Override
    public Categoria getById(int id) {
        return categoriaRepository.findById(id)
                .orElseThrow(()-> new CustomException(HttpStatus.NOT_FOUND,"Esa categoria no existe"));
    }

    @Override
    public Mensaje delete(int id) {
        Categoria categoria = getById(id);
        categoriaRepository.delete(categoria);
        return new Mensaje(categoria.getNombre() + "a sido Eliminado exitosamente");

    }

    @Override
    public Categoria getByNombre(String nombre) {
        return categoriaRepository.findByNombre(nombre)
                .orElseThrow(()-> new CustomException(HttpStatus.NOT_FOUND,"Esa categoria no existe"));
    }*/
}

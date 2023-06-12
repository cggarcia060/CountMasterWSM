package com.cgsoft.ws.security.service;

import com.cgsoft.ws.dto.Mensaje;
import com.cgsoft.ws.exceptions.CustomException;
import com.cgsoft.ws.security.dto.RequestContainer;
import com.cgsoft.ws.security.dto.RolDto;
import com.cgsoft.ws.security.entity.Rol;
import com.cgsoft.ws.security.enums.RolNombre;
import com.cgsoft.ws.security.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RolService {

    @Autowired
    RolRepository rolRepository;


    public List<Rol> getRoles(Rol rol){
        if (rol.getRolNombre().equals("ROLE_SUPERADMIN")){
            return rolRepository.findAll();
        }else{
            return  rolRepository.findByRolNombreNot("ROLE_SUPERADMIN");
        }
    }
    public Rol getByRolNombre(String rolNombre){
       return rolRepository.findByRolNombre(rolNombre)
               .orElseThrow(()-> new CustomException(HttpStatus.NOT_FOUND,"ese rol no existe"));
    }
    public Rol getRolById(int id){
       return rolRepository.findById(id)
               .orElseThrow(()-> new CustomException(HttpStatus.NOT_FOUND,"ese rol no existe"));
    }

    public Mensaje delete(int id){
        rolRepository.deleteById(id);
        return new Mensaje("Rol eliminado exitosamente");
    }

    public Mensaje save(RolDto rolDto){
        if(rolRepository.existsByNombre(rolDto.getNombre()))
            throw new CustomException(HttpStatus.NOT_FOUND,"Ese nombre de rol ya existe");
        if(rolRepository.existsByRolNombre(rolDto.getRolNombre()))
            throw new CustomException(HttpStatus.NOT_FOUND,"Ese codigo de rol ya existe");
        Rol rol=new Rol();
        rol.setNombre(rolDto.getNombre());
        rol.setRolNombre(rolDto.getRolNombre());
        rolRepository.save(rol);
        return new Mensaje("Rol guardado exitosamente");
    }

    public Mensaje update(RolDto rolDto){
        if(rolRepository.existsByNombreAndIdNot(rolDto.getNombre(),rolDto.getId()))
            throw new CustomException(HttpStatus.NOT_FOUND,"Ese nombre de rol ya existe");
        if(rolRepository.existsByRolNombreAndIdNot(rolDto.getRolNombre(), rolDto.getId()))
            throw new CustomException(HttpStatus.NOT_FOUND,"Ese codigo de rol ya existe");
        Rol rol=getRolById(rolDto.getId());
        rol.setNombre(rolDto.getNombre());
        rol.setRolNombre(rolDto.getRolNombre());
        rolRepository.save(rol);
        return new Mensaje("Rol editado exitosamente");
    }
}

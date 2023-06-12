package com.cgsoft.ws.security.service;


import com.cgsoft.ws.dto.Mensaje;
import com.cgsoft.ws.exceptions.CustomException;
import com.cgsoft.ws.security.dto.ProcesoDto;
import com.cgsoft.ws.security.dto.RequestContainer;
import com.cgsoft.ws.security.entity.Proceso;
import com.cgsoft.ws.security.entity.Rol;
import com.cgsoft.ws.security.repository.ProcesoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProcesoService {
    @Autowired
    ProcesoRepository procesoRepository;

    public List<Proceso> listProceso(Proceso proceso){
        if (proceso.getNombre().equals("CountMaster")){
            return procesoRepository.findAll();
        }else{
            return procesoRepository.findProcesoByNombreNot("CountMaster");
        }

    }

    public Proceso  getProcesoByNombre(String nombre){
        return procesoRepository.findProcesoByNombre(nombre)
                .orElseThrow(()-> new  CustomException(HttpStatus.NOT_FOUND,"Ese nombre de proceso no existe"));
    }

    public Mensaje save(ProcesoDto procesoDto){
        if(procesoRepository.existsByNombre(procesoDto.getNombre()))
            throw  new CustomException(HttpStatus.NOT_FOUND,"Ese nombre de proceso ya existe");
        if(procesoRepository.existsByCodigo(procesoDto.getCodigo()))
            throw new CustomException(HttpStatus.NOT_FOUND,"Ese codigo ya existe");
        Proceso proceso =new Proceso();
        proceso.setNombre(procesoDto.getNombre());
        proceso.setCodigo(procesoDto.getCodigo());
        procesoRepository.save(proceso);
        return  new Mensaje("Proceso guardado exitosamente");

    }
    public Mensaje update(ProcesoDto procesoDto){
        if(procesoRepository.existsByNombreAndIdNot(procesoDto.getNombre(),procesoDto.getId()))
            throw  new CustomException(HttpStatus.NOT_FOUND,"Ese nombre de proceso ya existe");
        if(procesoRepository.existsByCodigoAndIdNot(procesoDto.getCodigo(), procesoDto.getId()))
            throw new CustomException(HttpStatus.NOT_FOUND,"Ese codigo ya existe");
        Proceso proceso =getProcesoById(procesoDto.getId());
        proceso.setNombre(procesoDto.getNombre());
        proceso.setCodigo(procesoDto.getCodigo());
        procesoRepository.save(proceso);
        return  new Mensaje("Proceso editado exitosamente");

    }

    public Mensaje delete(int id){
        procesoRepository.deleteById(id);
        return  new Mensaje("Proceso eliminado exitosamente");
    }

    public Proceso getProcesoById(int id){
        return procesoRepository.findById(id)
                .orElseThrow(()->new CustomException(HttpStatus.NOT_FOUND,"Ese proceso no existe"));
    }
}

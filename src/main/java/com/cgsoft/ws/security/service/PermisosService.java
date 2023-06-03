package com.cgsoft.ws.security.service;


import com.cgsoft.ws.dto.Mensaje;
import com.cgsoft.ws.entity.Categoria;
import com.cgsoft.ws.exceptions.CustomException;
import com.cgsoft.ws.security.dto.PermisoDto;
import com.cgsoft.ws.security.dto.ProcesoDto;
import com.cgsoft.ws.security.dto.RolAndProcesoDto;
import com.cgsoft.ws.security.entity.Permisos;
import com.cgsoft.ws.security.entity.Proceso;
import com.cgsoft.ws.security.entity.Rol;
import com.cgsoft.ws.security.enums.RolNombre;
import com.cgsoft.ws.security.repository.PermisosRepository;
import com.cgsoft.ws.utils.ScriptionElement;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class PermisosService  {


    @Autowired
    PermisosRepository permisosRepository;

    @Autowired
    RolService rolService;

    @Autowired
    ProcesoService procesoService;

    @Autowired
    ScriptionElement scriptionElement;
    public Permisos getPermisos(int id){
      return   permisosRepository.findById(id)
                .orElseThrow(()-> new CustomException(HttpStatus.NOT_FOUND,"no se encontro permisos"));
    }
    public  List<Permisos>  listAll() {
        return permisosRepository.findAll();
    }
    public Map<String,String> getRolesByProceso(RolAndProcesoDto rolAndProcesoDto){
        try {
            Map<String,String> roles=new HashMap<>();
            ObjectMapper objectMapper = new ObjectMapper();
            String idProceso;
            String idRol;
            try {

                idProceso=scriptionElement.decrypt(rolAndProcesoDto.getProceso());
                idRol=scriptionElement.decrypt(rolAndProcesoDto.getRol());
            }catch (Exception e){
                throw new CustomException(HttpStatus.NOT_FOUND,"proceso o rol no existen");
            }
            Proceso proceso=procesoService.getProcesoById(Integer.parseInt(idProceso));
            List<Permisos> permisos = permisosRepository.findByProceso_id(proceso.getId());
            List<Permisos> permisoByRol= permisos.stream().filter(x-> x.getRol().getId()==Integer.parseInt(idRol))
                    .collect(Collectors.toList());
/*
            List<Permisos> permisoByRol= permisos.stream().filter(x-> x.getRolId().getRolNombre()==rolAndProcesoDto.getRol())
                    .collect(Collectors.toList());
  */
            if (permisoByRol.isEmpty()){
                throw new CustomException(HttpStatus.FORBIDDEN,"El usuario no tiene permisos");
            }
            String jsonString = objectMapper.writeValueAsString(permisos);
            roles.put("PRP",ScriptionElement.encrypt(jsonString));
            return  roles;
        }catch (Exception e){
            throw new CustomException(HttpStatus.FORBIDDEN,"El usuario no tiene permisos");
//            throw new CustomException(HttpStatus.BAD_REQUEST,"Hubo un error al cargar roles");
        }
    }

    public Mensaje save(PermisoDto permisoDto){
        if(permisosRepository.existsByRolAndProceso(permisoDto.getRol(),permisoDto.getProceso()))
            throw  new CustomException(HttpStatus.NOT_FOUND,"Ese permiso con proceso y rol ya existe");
        Permisos permiso=new Permisos();
        permiso.setRol(permisoDto.getRol());
        permiso.setProceso(permisoDto.getProceso());
        permiso.setAdministracion(permisoDto.isAdministracion());
        permiso.setCategorias(permisoDto.isCategorias());
        permiso.setCompras(permisoDto.isCompras());
        permiso.setConfiguracion(permisoDto.isConfiguracion());
        permiso.setContabilidad(permisoDto.isContabilidad());
        permiso.setDashboard(permisoDto.isDashboard());
        permiso.setInventario(permisoDto.isInventario());
        permiso.setProveedores(permisoDto.isProveedores());
        permiso.setVentas(permisoDto.isVentas());
        permiso.setUsuarios(permisoDto.isUsuarios());
        permiso.setProductos(permisoDto.isProductos());
        permisosRepository.save(permiso);
        return  new Mensaje("Permiso guardado exitosamente");
    }

    public Mensaje update(PermisoDto permisoDto){
        if(permisosRepository.existsByRolAndProcesoAndIdNot(permisoDto.getRol(),permisoDto.getProceso(),permisoDto.getId()))
            throw  new CustomException(HttpStatus.NOT_FOUND,"Ese permiso con proceso y rol ya existe");

        Permisos permiso=getPermisos(permisoDto.getId());
        permiso.setRol(permisoDto.getRol());
        permiso.setProceso(permisoDto.getProceso());
        permiso.setAdministracion(permisoDto.isAdministracion());
        permiso.setCategorias(permisoDto.isCategorias());
        permiso.setCompras(permisoDto.isCompras());
        permiso.setConfiguracion(permisoDto.isConfiguracion());
        permiso.setContabilidad(permisoDto.isContabilidad());
        permiso.setDashboard(permisoDto.isDashboard());
        permiso.setInventario(permisoDto.isInventario());
        permiso.setProveedores(permisoDto.isProveedores());
        permiso.setVentas(permisoDto.isVentas());
        permiso.setUsuarios(permisoDto.isUsuarios());
        permiso.setProductos(permisoDto.isProductos());
        permisosRepository.save(permiso);
        return  new Mensaje("Permiso editado exitosamente");
    }

    public Mensaje delete(int id){
        permisosRepository.deleteById(id);
        return  new Mensaje("Permiso eliminado exitosamente");
    }
}

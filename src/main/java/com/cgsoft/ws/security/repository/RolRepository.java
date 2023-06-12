package com.cgsoft.ws.security.repository;


import com.cgsoft.ws.security.entity.Permisos;
import com.cgsoft.ws.security.entity.Proceso;
import com.cgsoft.ws.security.entity.Rol;
import com.cgsoft.ws.security.enums.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol , Integer> {

    Optional<Rol> findByRolNombre(String rolNombre);
    boolean existsByNombre(String nombre);
    boolean existsByRolNombre(String rolNombre);
    boolean  existsByNombreAndIdNot(String nombre,int id);
    boolean existsByRolNombreAndIdNot(String rolNombre,int id);
    List<Rol> findByRolNombreNot(String rolNombre);



}

package com.cgsoft.ws.security.repository;

import com.cgsoft.ws.entity.Categoria;
import com.cgsoft.ws.security.entity.Permisos;
import com.cgsoft.ws.security.entity.Proceso;
import com.cgsoft.ws.security.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PermisosRepository extends JpaRepository<Permisos,Integer> {

    /*
    @Query(value = "SELECT * FROM permisos WHERE rol_id=:estado", nativeQuery=true)
    List<Permisos> findByRolAndProceso(boolean estado);
*/
    List<Permisos> findByProceso_id(@Param("procesoId") int procesoId);
    boolean existsByRolAndProceso(Rol rol, Proceso proceso);
    boolean existsByRolAndProcesoAndIdNot(Rol rol, Proceso proceso,int id);


}

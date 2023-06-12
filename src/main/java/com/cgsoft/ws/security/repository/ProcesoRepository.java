package com.cgsoft.ws.security.repository;

import com.cgsoft.ws.security.entity.Proceso;
import com.cgsoft.ws.security.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProcesoRepository  extends JpaRepository<Proceso,Integer> {
    Optional<Proceso> findProcesoByNombre(String nombre);

    @Override
    Optional<Proceso> findById(Integer integer);

    boolean existsByNombre(String nombre);
    boolean existsByCodigo(String codigo);
    boolean existsByNombreAndIdNot(String nombre,int id);
    boolean existsByCodigoAndIdNot(String codigo,int id);

    List<Proceso> findProcesoByNombreNot(String nombre);

}
